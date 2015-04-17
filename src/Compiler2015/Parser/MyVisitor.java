package Compiler2015.Parser;

import Compiler2015.AST.ASTNode;
import Compiler2015.AST.ASTRoot;
import Compiler2015.AST.Statement.*;
import Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression.*;
import Compiler2015.AST.Statement.ExpressionStatement.*;
import Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression.*;
import Compiler2015.AST.Type.*;
import Compiler2015.Environment.Environment;
import Compiler2015.Exception.CompilationError;
import Compiler2015.Utility.Tokens;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;

public class MyVisitor extends Compiler2015BaseVisitor<Object> {

	Integer toInt(Expression x) {
		if (x instanceof IntConstant)
			return ((IntConstant) x).c;
		if (x instanceof CharConstant)
			return (int) (((CharConstant) x).c);
		return null;
	}

	@Override
	public Object visitPrimaryExpression1(@NotNull Compiler2015Parser.PrimaryExpression1Context ctx) {
		String name = ctx.Identifier.getText();
		ctx.ret = new Identifier(name, new IntType(), Environment.symbolNames.queryName(name).type == Tokens.VARIABLE);
		return super.visitPrimaryExpression1(ctx);
	}

	@Override
	public Object visitPrimaryExpression2(@NotNull Compiler2015Parser.PrimaryExpression2Context ctx) {
		visit(ctx.constant());
		ctx.ret = ctx.constant().ret;
		return super.visitPrimaryExpression2(ctx);
	}

	@Override
	public Object visitPrimaryExpression3(@NotNull Compiler2015Parser.PrimaryExpression3Context ctx) {
		StringBuilder sb = new StringBuilder();
		for (TerminalNode n : ctx.StringLiteral()) {
			String str = n.getText();
			sb.append(str.substring(1, str.length() - 1));
		}
		// TODO: length would be wrong if string contains ESCAPE CHARACTERs.
		ctx.ret = new StringConstant(
				sb.toString(),
				new ArrayPointerType(new CharType(), sb.length()),
				false);
		return super.visitPrimaryExpression3(ctx);
	}

	@Override
	public Object visitPrimaryExpression4(@NotNull Compiler2015Parser.PrimaryExpression4Context ctx) {
		visit(ctx.expression());
		ctx.ret = ctx.expression().ret;
		return super.visitPrimaryExpression4(ctx);
	}

	@Override
	public Object visitPostfixExpression1(@NotNull Compiler2015Parser.PostfixExpression1Context ctx) {
		visit(ctx.primaryExpression());
		ctx.ret = ctx.primaryExpression().ret;
		return super.visitPostfixExpression1(ctx);
	}

	@Override
	public Object visitPostfixExpression2(@NotNull Compiler2015Parser.PostfixExpression2Context ctx) {
		visit(ctx.postfixExpression());
		visit(ctx.expression());
		Expression a1 = ctx.postfixExpression().ret;
		Expression a2 = ctx.expression().ret;
		if (a1.type instanceof VariablePointerType)
			ctx.ret = new ArrayAccess(a1, a2, ((VariablePointerType) a1.type).pointTo, true);
		else if (a1.type instanceof ArrayPointerType) {
			// TODO: What should I do?
		}
		return super.visitPostfixExpression2(ctx);
	}

	@Override
	public Object visitPostfixExpression3(@NotNull Compiler2015Parser.PostfixExpression3Context ctx) {
		// TODO: ArrayPointerType -> VariablePointerType
		visit(ctx.postfixExpression());
		visit(ctx.argumentExpressionList());
		Type lType = ctx.postfixExpression().ret.type;
		if (!(lType instanceof FunctionPointerType))
			throw new CompilationError("Not a pointer to function.");
		FunctionPointerType l = (FunctionPointerType)lType;
		int size = l.ref.parameterTypes.length;
		if ((size == 0) != (ctx.argumentExpressionList() == null)) {
			throw new CompilationError("Parameter number mismatch.");
		}
		ArrayList<Expression> list = ctx.argumentExpressionList().ret;
		if (size != list.size()) {
			throw new CompilationError("Parameter number mismatch.");
		}
		for (int i = 0; i < size; ++i) {
			if (!Type.suitable(l.ref.parameterTypes[i], list.get(i).type))
				throw new CompilationError("Parameter type mismatch.");
		}
		Expression argList[] = new Expression[size];
		for (int i = 0; i < size; ++i)
			argList[i] = list.get(i);
		ctx.ret = new FunctionCall(l, argList, l.ref.returnType, false);
		return super.visitPostfixExpression3(ctx);
	}

	@Override
	public Object visitPostfixExpression4(@NotNull Compiler2015Parser.PostfixExpression4Context ctx) {
		visit(ctx.postfixExpression());
		visit(ctx.Identifier());
		Expression a1 = ctx.postfixExpression().ret;
		String a2 = ctx.Identifier().getText();
		if (!(a1.type instanceof StructOrUnionType))
			throw new CompilationError("Basic type cannot have members.");
		StructOrUnionType leftType = (StructOrUnionType)a1.type;
		Type memberType = leftType.ref.memberType(a2);
		if (memberType == null)
			throw new CompilationError("No such member.");
		ctx.ret = new MemberAccess(a1, a2, memberType, true);
		return super.visitPostfixExpression4(ctx);
	}

	@Override
	public Object visitPostfixExpression5(@NotNull Compiler2015Parser.PostfixExpression5Context ctx) {
		visit(ctx.postfixExpression());
		visit(ctx.Identifier());
		Expression a1 = ctx.postfixExpression().ret;
		String a2 = ctx.Identifier().getText();
		Type l = Pointer.getPointTo(a1.type);
		if (!(l instanceof StructOrUnionType))
			throw new CompilationError("Must be a pointer to a certain struct/union");
		StructOrUnionType leftType = (StructOrUnionType)l;
		Type memberType = leftType.ref.memberType(a2);
		if (memberType == null)
			throw new CompilationError("No such member.");
		ctx.ret = new PointerMemberAccess(a1, a2, memberType, true);
		return super.visitPostfixExpression5(ctx);
	}

	@Override
	public Object visitPostfixExpression6(@NotNull Compiler2015Parser.PostfixExpression6Context ctx) {
		visit(ctx.postfixExpression());
		Expression a1 = ctx.postfixExpression().ret;
		if (!a1.isLValue)
			throw new CompilationError("Not LValue.");
		if (a1.type instanceof VoidType || a1.type instanceof StructOrUnionType)
			throw new CompilationError("Such type supports no self-increment.");
		if (a1 instanceof IntConstant) {
			++((IntConstant) a1).c;
			a1.isLValue = false;
			ctx.ret = a1;
		} else if (a1 instanceof CharConstant) {
			++((CharConstant) a1).c;
			a1.isLValue = false;
			ctx.ret = a1;
		} else
			ctx.ret = new PostfixSelfInc(a1, a1.type, false);
		return super.visitPostfixExpression6(ctx);
	}

	@Override
	public Object visitPostfixExpression7(@NotNull Compiler2015Parser.PostfixExpression7Context ctx) {
		visit(ctx.postfixExpression());
		Expression a1 = ctx.postfixExpression().ret;
		if (!a1.isLValue)
			throw new CompilationError("Not LValue.");
		if (a1.type instanceof VoidType || a1.type instanceof StructOrUnionType)
			throw new CompilationError("Such type supports no self-decrement.");
		if (a1 instanceof IntConstant) {
			--((IntConstant) a1).c;
			a1.isLValue = false;
			ctx.ret = a1;
		} else if (a1 instanceof CharConstant) {
			--((CharConstant) a1).c;
			a1.isLValue = false;
			ctx.ret = a1;
		} else
			ctx.ret = new PostfixSelfDec(a1, a1.type, false);
		return super.visitPostfixExpression7(ctx);
	}

	@Override
	public Object visitArgumentExpressionList1(@NotNull final Compiler2015Parser.ArgumentExpressionList1Context ctx) {
		visit(ctx.assignmentExpression());
		ctx.ret = new ArrayList<Expression>() {{add(ctx.assignmentExpression().ret);}};
		return super.visitArgumentExpressionList1(ctx);
	}

	@Override
	public Object visitArgumentExpressionList2(@NotNull Compiler2015Parser.ArgumentExpressionList2Context ctx) {
		visit(ctx.argumentExpressionList());
		visit(ctx.assignmentExpression());
		ctx.ret = ctx.argumentExpressionList().ret;
		ctx.ret.add(ctx.assignmentExpression().ret);
		return super.visitArgumentExpressionList2(ctx);
	}

	@Override
	public Object visitUnaryExpression1(@NotNull Compiler2015Parser.UnaryExpression1Context ctx) {
		visit(ctx.postfixExpression());
		ctx.ret = ctx.postfixExpression().ret;
		return super.visitUnaryExpression1(ctx);
	}

	@Override
	public Object visitUnaryExpression2(@NotNull Compiler2015Parser.UnaryExpression2Context ctx) {
		visit(ctx.unaryExpression());
		Expression a1 = ctx.unaryExpression().ret;
		if (!a1.isLValue)
			throw new CompilationError("Not LValue.");
		if (a1.type instanceof VoidType || a1.type instanceof StructOrUnionType)
			throw new CompilationError("Such type supports no self-increment.");
		if (a1 instanceof IntConstant) {
			++((IntConstant) a1).c;
			a1.isLValue = false;
			ctx.ret = a1;
		} else if (a1 instanceof CharConstant) {
			++((CharConstant) a1).c;
			a1.isLValue = false;
			ctx.ret = a1;
		} else
			ctx.ret = new PrefixSelfInc(a1, a1.type, false);
		return super.visitUnaryExpression2(ctx);
	}

	@Override
	public Object visitUnaryExpression3(@NotNull Compiler2015Parser.UnaryExpression3Context ctx) {
		visit(ctx.unaryExpression());
		Expression a1 = ctx.unaryExpression().ret;
		if (!a1.isLValue)
			throw new CompilationError("Not LValue.");
		if (a1.type instanceof VoidType || a1.type instanceof StructOrUnionType)
			throw new CompilationError("Such type supports no self-decrement.");
		if (a1 instanceof IntConstant) {
			--((IntConstant) a1).c;
			a1.isLValue = false;
			ctx.ret = a1;
		} else if (a1 instanceof CharConstant) {
			--((CharConstant) a1).c;
			a1.isLValue = false;
			ctx.ret = a1;
		} else
			ctx.ret = new PrefixSelfDec(a1, a1.type, false);
		return super.visitUnaryExpression3(ctx);
	}

	@Override
	public Object visitUnaryExpression4(@NotNull Compiler2015Parser.UnaryExpression4Context ctx) {
		visit(ctx.castExpression());
		Expression e = ctx.castExpression().ret;
		if (!e.isLValue)
			throw new CompilationError("Not LValue.");
		ctx.ret = new AddressFetch(e, new VariablePointerType(e.type), false);
		return super.visitUnaryExpression4(ctx);
	}

	@Override
	public Object visitUnaryExpression5(@NotNull Compiler2015Parser.UnaryExpression5Context ctx) {
		visit(ctx.castExpression());
		Expression e = ctx.castExpression().ret;
		ctx.ret = new AddressAccess(e, Pointer.getPointTo(e.type), true);
		return super.visitUnaryExpression5(ctx);
	}

	@Override
	public Object visitUnaryExpression6(@NotNull Compiler2015Parser.UnaryExpression6Context ctx) {
		visit(ctx.castExpression());
		Expression e = ctx.castExpression().ret;
		if (!Type.isNumeric(e.type))
			throw new CompilationError("Cannot put unary plus before this type");
		ctx.ret = e;
		ctx.ret.type = new IntType();
		ctx.ret.isLValue = false;
		return super.visitUnaryExpression6(ctx);
	}

	@Override
	public Object visitUnaryExpression7(@NotNull Compiler2015Parser.UnaryExpression7Context ctx) {
		visit(ctx.castExpression());
		Expression e = ctx.castExpression().ret;
		if (!Type.isNumeric(e.type))
			throw new CompilationError("Cannot put unary plus before this type");
		Integer v = toInt(e);
		if (v != null) {
			ctx.ret = new IntConstant(-v, new IntType(), false);
		} else {
			ctx.ret = new Negative(e, e.type, false);
		}
		return super.visitUnaryExpression7(ctx);
	}

	@Override
	public Object visitUnaryExpression8(@NotNull Compiler2015Parser.UnaryExpression8Context ctx) {
		visit(ctx.castExpression());
		Expression e = ctx.castExpression().ret;
		if (!Type.isNumeric(e.type))
			throw new CompilationError("Cannot put unary plus before this type");
		Integer v = toInt(e);
		if (v != null) {
			ctx.ret = new IntConstant(~v, new IntType(), false);
		} else
			ctx.ret = new BitwiseNot(e, e.type, false);
		return super.visitUnaryExpression8(ctx);
	}

	@Override
	public Object visitUnaryExpression9(@NotNull Compiler2015Parser.UnaryExpression9Context ctx) {
		visit(ctx.castExpression());
		Expression e = ctx.castExpression().ret;
		if (!Type.isNumeric(e.type))
			throw new CompilationError("Cannot put unary plus before this type");
		Integer v = toInt(e);
		if (v != null) {
			ctx.ret = new IntConstant(v != 0 ? 0 : 1, new IntType(), false);
		} else {
			ctx.ret = new LogicalNot(e, e.type, false);
		}
		return super.visitUnaryExpression9(ctx);
	}

	@Override
	public Object visitUnaryExpression10(@NotNull Compiler2015Parser.UnaryExpression10Context ctx) {
		visit(ctx.unaryExpression());
		Expression e = ctx.unaryExpression().ret;
		if (e instanceof StringConstant || e instanceof CharConstant || e instanceof IntConstant)
			ctx.ret = new IntConstant(e.sizeof(), new IntType(), false);
		else
			ctx.ret = new Sizeof(e, new IntType(), false);
		return super.visitUnaryExpression10(ctx);
	}

	@Override
	public Object visitUnaryExpression11(@NotNull Compiler2015Parser.UnaryExpression11Context ctx) {
		visit(ctx.typeName());
		ctx.ret = new IntConstant(ctx.ret.sizeof(), new IntType(), false);
		return super.visitUnaryExpression11(ctx);
	}

	@Override
	public Object visitCastExpression1(@NotNull Compiler2015Parser.CastExpression1Context ctx) {
		visit(ctx.unaryExpression());
		ctx.ret = ctx.unaryExpression().ret;
		return super.visitCastExpression1(ctx);
	}

	@Override
	public Object visitCastExpression2(@NotNull Compiler2015Parser.CastExpression2Context ctx) {
		visit(ctx.typeName());
		visit(ctx.castExpression());
		Type t = (Type)ctx.typeName().ret;
		Expression c = ctx.castExpression().ret;
		if (t instanceof VoidType)
			throw new CompilationError("Cannot cast to void");
		if (Type.sameType(t, c.type)) { // no need to cast
			ctx.ret = c;
		}
		else {
			if (t instanceof StructOrUnionType || c.type instanceof StructOrUnionType)
				throw new CompilationError("Cast failed");
			// Warning: pointer can be cast to int, but it is allowed in gcc
			if (c instanceof IntConstant) {
				int v = ((IntConstant) c).c;
				ctx.ret = new CharConstant((char) v, new CharType(), false);
			} else if (c instanceof CharConstant) {
				char v = ((CharConstant) c).c;
				ctx.ret = new IntConstant((int) v, new IntType(), false);
			} else
				ctx.ret = new CastExpression(t, c, t, false);
		}
		return super.visitCastExpression2(ctx);
	}

	@Override
	public Object visitMultiplicativeExpression1(@NotNull Compiler2015Parser.MultiplicativeExpression1Context ctx) {
		visit(ctx.castExpression());
		ctx.ret = ctx.castExpression().ret;
		return super.visitMultiplicativeExpression1(ctx);
	}

	@Override
	public Object visitMultiplicativeExpression2(@NotNull Compiler2015Parser.MultiplicativeExpression2Context ctx) {
		visit(ctx.multiplicativeExpression());
		visit(ctx.castExpression());
		Expression a1 = ctx.multiplicativeExpression().ret;
		Expression a2 = ctx.castExpression().ret;
		if (!Type.isNumeric(a1.type) || !Type.isNumeric(a2.type))
			throw new CompilationError("This type does not support numeric operation");
		Integer v1 = toInt(a1), v2 = toInt(a2);
		if (v1 != null && v2 != null) {
			ctx.ret = new IntConstant(v1 * v2, new IntType(), false);
		} else {
			if (a1.type instanceof CharType && a2.type instanceof IntType)
				a1 = new CastExpression(new IntType(), a1, new IntType(), false);
			else if (a1.type instanceof IntType && a2.type instanceof CharType)
				a2 = new CastExpression(new IntType(), a2, new IntType(), false);
			ctx.ret = new Multiply(a1, a2, a1.type, false);
		}
		return super.visitMultiplicativeExpression2(ctx);
	}

	@Override
	public Object visitMultiplicativeExpression3(@NotNull Compiler2015Parser.MultiplicativeExpression3Context ctx) {
		visit(ctx.multiplicativeExpression());
		visit(ctx.castExpression());
		Expression a1 = ctx.multiplicativeExpression().ret;
		Expression a2 = ctx.castExpression().ret;
		if (!Type.isNumeric(a1.type) || !Type.isNumeric(a2.type))
			throw new CompilationError("This type does not support numeric operation");
		Integer v1 = toInt(a1), v2 = toInt(a2);
		if (v1 != null && v2 != null) {
			ctx.ret = new IntConstant(v1 / v2, new IntType(), false);
		} else {
			if (a1.type instanceof CharType && a2.type instanceof IntType)
				a1 = new CastExpression(new IntType(), a1, new IntType(), false);
			else if (a1.type instanceof IntType && a2.type instanceof CharType)
				a2 = new CastExpression(new IntType(), a2, new IntType(), false);
			ctx.ret = new Divide(a1, a2, a1.type, false);
		}
		return super.visitMultiplicativeExpression3(ctx);
	}

	@Override
	public Object visitMultiplicativeExpression4(@NotNull Compiler2015Parser.MultiplicativeExpression4Context ctx) {
		visit(ctx.multiplicativeExpression());
		visit(ctx.castExpression());
		Expression a1 = ctx.multiplicativeExpression().ret;
		Expression a2 = ctx.castExpression().ret;
		if (!Type.isNumeric(a1.type) || !Type.isNumeric(a2.type))
			throw new CompilationError("This type does not support numeric operation");
		Integer v1 = toInt(a1), v2 = toInt(a2);
		if (v1 != null && v2 != null) {
			ctx.ret = new IntConstant(v1 % v2, new IntType(), false);
		} else {
			if (a1.type instanceof CharType && a2.type instanceof IntType)
				a1 = new CastExpression(new IntType(), a1, new IntType(), false);
			else if (a1.type instanceof IntType && a2.type instanceof CharType)
				a2 = new CastExpression(new IntType(), a2, new IntType(), false);
			ctx.ret = new Modulo(a1, a2, a1.type, false);
		}
		return super.visitMultiplicativeExpression4(ctx);
	}

	@Override
	public Object visitAdditiveExpression1(@NotNull Compiler2015Parser.AdditiveExpression1Context ctx) {
		visit(ctx.multiplicativeExpression());
		ctx.ret = ctx.multiplicativeExpression().ret;
		return super.visitAdditiveExpression1(ctx);
	}

	@Override
	public Object visitAdditiveExpression2(@NotNull Compiler2015Parser.AdditiveExpression2Context ctx) {
		visit(ctx.additiveExpression());
		visit(ctx.multiplicativeExpression());
		Expression a1 = ctx.additiveExpression().ret;
		Expression a2 = ctx.multiplicativeExpression().ret;
		if (a1.type instanceof VoidType || a2.type instanceof VoidType)
			throw new CompilationError("Cannot operate add on void type.");
		if (a1.type instanceof FunctionPointerType || a2.type instanceof FunctionPointerType)
			throw new CompilationError("Cannot operate add on pointer to function.");
		if (a1.type instanceof StructOrUnionType || a2.type instanceof StructOrUnionType)
			throw new CompilationError("Cannot operate add on struct or unions.");
		if (a1.type instanceof Pointer && a2.type instanceof Pointer)
			throw new CompilationError("Cannot add two pointers.");
		Integer v1 = toInt(a1), v2 = toInt(a2);
		if (v1 != null && v2 != null) {
			ctx.ret = new IntConstant(v1 + v2, new IntType(), false);
		} else {
			if (a1.type instanceof CharType && a2.type instanceof IntType)
				a1 = new CastExpression(new IntType(), a1, new IntType(), false);
			else if (a1.type instanceof IntType && a2.type instanceof CharType)
				a2 = new CastExpression(new IntType(), a2, new IntType(), false);
			ctx.ret = new Add(a1, a2, (a2.type instanceof Pointer) ? a2.type : a1.type, false);
		}
		return super.visitAdditiveExpression2(ctx);
	}

	@Override
	public Object visitAdditiveExpression3(@NotNull Compiler2015Parser.AdditiveExpression3Context ctx) {
		visit(ctx.additiveExpression());
		visit(ctx.multiplicativeExpression());
		Expression a1 = ctx.additiveExpression().ret;
		Expression a2 = ctx.multiplicativeExpression().ret;
		if (a1.type instanceof VoidType || a2.type instanceof VoidType)
			throw new CompilationError("Cannot operate minus on void type.");
		if (a1.type instanceof Pointer && a2.type instanceof Pointer && !Type.sameType(a1.type, a2.type))
			throw new CompilationError("Cannot operate minus on pointers to different types.");
		if (a1.type instanceof StructOrUnionType || a2.type instanceof StructOrUnionType)
			throw new CompilationError("Cannot operate minus on struct or unions.");
		Integer v1 = toInt(a1), v2 = toInt(a2);
		if (v1 != null && v2 != null) {
			ctx.ret = new IntConstant(v1 * v2, new IntType(), false);
		} else {
			if (a1.type instanceof CharType && a2.type instanceof IntType)
				a1 = new CastExpression(new IntType(), a1, new IntType(), false);
			else if (a1.type instanceof IntType && a2.type instanceof CharType)
				a2 = new CastExpression(new IntType(), a2, new IntType(), false);
			ctx.ret = new Subtract(a1, a2, (a2.type instanceof Pointer) ? a2.type : a1.type, false);
		}
		return super.visitAdditiveExpression3(ctx);
	}

	@Override
	public Object visitShiftExpression1(@NotNull Compiler2015Parser.ShiftExpression1Context ctx) {
		visit(ctx.additiveExpression());
		ctx.ret = ctx.additiveExpression().ret;
		return super.visitShiftExpression1(ctx);
	}

	@Override
	public Object visitShiftExpression2(@NotNull Compiler2015Parser.ShiftExpression2Context ctx) {
		visit(ctx.shiftExpression());
		visit(ctx.additiveExpression());
		Expression a1 = ctx.shiftExpression().ret;
		Expression a2 = ctx.additiveExpression().ret;
		if (!Type.isNumeric(a1.type) || !Type.isNumeric(a2.type))
			throw new CompilationError("<< must be operated on numeric types");
		Integer v1 = toInt(a1), v2 = toInt(a2);
		if (v1 != null && v2 != null) {
			ctx.ret = new IntConstant(v1 << v2, new IntType(), false);
		} else {
			if (!(a1.type instanceof IntType))
				a1 = new CastExpression(new IntType(), a1, new IntType(), false);
			if (!(a2.type instanceof IntType))
				a2 = new CastExpression(new IntType(), a2, new IntType(), false);
			ctx.ret = new ShiftLeft(a1, a2, new IntType(), false);
		}
		return super.visitShiftExpression2(ctx);
	}

	@Override
	public Object visitShiftExpression3(@NotNull Compiler2015Parser.ShiftExpression3Context ctx) {
		visit(ctx.shiftExpression());
		visit(ctx.additiveExpression());
		Expression a1 = ctx.shiftExpression().ret;
		Expression a2 = ctx.additiveExpression().ret;
		if (!Type.isNumeric(a1.type) || !Type.isNumeric(a2.type))
			throw new CompilationError(">> must be operated on numeric types");
		Integer v1 = toInt(a1), v2 = toInt(a2);
		if (v1 != null && v2 != null) {
			ctx.ret = new IntConstant(v1 >> v2, new IntType(), false);
		} else {
			if (!(a1.type instanceof IntType))
				a1 = new CastExpression(new IntType(), a1, new IntType(), false);
			if (!(a2.type instanceof IntType))
				a2 = new CastExpression(new IntType(), a2, new IntType(), false);
			ctx.ret = new ShiftRight(a1, a2, new IntType(), false);
		}
		return super.visitShiftExpression3(ctx);
	}

	@Override
	public Object visitRelationalExpression1(@NotNull Compiler2015Parser.RelationalExpression1Context ctx) {
		visit(ctx.shiftExpression());
		ctx.ret = ctx.shiftExpression().ret;
		return super.visitRelationalExpression1(ctx);
	}

	@Override
	public Object visitRelationalExpression2(@NotNull Compiler2015Parser.RelationalExpression2Context ctx) {
		visit(ctx.relationalExpression());
		visit(ctx.shiftExpression());
		Expression a1 = ctx.relationalExpression().ret;
		Expression a2 = ctx.shiftExpression().ret;
		if (a1.type instanceof VoidType || a2.type instanceof VoidType)
			throw new CompilationError("Cannot compare void");
		if ((a1.type instanceof StructOrUnionType) || (a2.type instanceof StructOrUnionType))
			throw new CompilationError("Cannot compare struct/union");
		Integer v1 = toInt(a1), v2 = toInt(a2);
		if (v1 != null && v2 != null) {
			ctx.ret = new IntConstant(v1 < v2 ? 1 : 0, new IntType(), false);
		} else {
			// comparison between pointer and integer is allowed
			ctx.ret = new LessThan(a1, a2, new IntType(), false);
		}
		return super.visitRelationalExpression2(ctx);
	}

	@Override
	public Object visitRelationalExpression3(@NotNull Compiler2015Parser.RelationalExpression3Context ctx) {
		visit(ctx.relationalExpression());
		visit(ctx.shiftExpression());
		Expression a1 = ctx.relationalExpression().ret;
		Expression a2 = ctx.shiftExpression().ret;
		if (a1.type instanceof VoidType || a2.type instanceof VoidType)
			throw new CompilationError("Cannot compare void");
		if ((a1.type instanceof StructOrUnionType) || (a2.type instanceof StructOrUnionType))
			throw new CompilationError("Cannot compare struct/union");
		Integer v1 = toInt(a1), v2 = toInt(a2);
		if (v1 != null && v2 != null) {
			ctx.ret = new IntConstant(v1 > v2 ? 1 : 0, new IntType(), false);
		} else {
			// comparison between pointer and integer is allowed
			ctx.ret = new GreaterThan(a1, a2, new IntType(), false);
		}
		return super.visitRelationalExpression3(ctx);
	}

	@Override
	public Object visitRelationalExpression4(@NotNull Compiler2015Parser.RelationalExpression4Context ctx) {
		visit(ctx.relationalExpression());
		visit(ctx.shiftExpression());
		Expression a1 = ctx.relationalExpression().ret;
		Expression a2 = ctx.shiftExpression().ret;
		if (a1.type instanceof VoidType || a2.type instanceof VoidType)
			throw new CompilationError("Cannot compare void");
		if ((a1.type instanceof StructOrUnionType) || (a2.type instanceof StructOrUnionType))
			throw new CompilationError("Cannot compare struct/union");
		Integer v1 = toInt(a1), v2 = toInt(a2);
		if (v1 != null && v2 != null) {
			ctx.ret = new IntConstant(v1 <= v2 ? 1 : 0, new IntType(), false);
		} else {
			// comparison between pointer and integer is allowed
			ctx.ret = new LE(a1, a2, new IntType(), false);
		}
		return super.visitRelationalExpression4(ctx);
	}

	@Override
	public Object visitRelationalExpression5(@NotNull Compiler2015Parser.RelationalExpression5Context ctx) {
		visit(ctx.relationalExpression());
		visit(ctx.shiftExpression());
		Expression a1 = ctx.relationalExpression().ret;
		Expression a2 = ctx.shiftExpression().ret;
		if (a1.type instanceof VoidType || a2.type instanceof VoidType)
			throw new CompilationError("Cannot compare void");
		if ((a1.type instanceof StructOrUnionType) || (a2.type instanceof StructOrUnionType))
			throw new CompilationError("Cannot compare struct/union");
		Integer v1 = toInt(a1), v2 = toInt(a2);
		if (v1 != null && v2 != null) {
			ctx.ret = new IntConstant(v1 >= v2 ? 1 : 0, new IntType(), false);
		} else {
			// Comparison between pointer and integer is allowed
			ctx.ret = new GE(a1, a2, new IntType(), false);
		}
		return super.visitRelationalExpression5(ctx);
	}

	@Override
	public Object visitEqualityExpression1(@NotNull Compiler2015Parser.EqualityExpression1Context ctx) {
		visit(ctx.relationalExpression());
		ctx.ret = ctx.relationalExpression().ret;
		return super.visitEqualityExpression1(ctx);
	}

	@Override
	public Object visitEqualityExpression2(@NotNull Compiler2015Parser.EqualityExpression2Context ctx) {
		visit(ctx.equalityExpression());
		visit(ctx.relationalExpression());
		Expression a1 = ctx.equalityExpression().ret;
		Expression a2 = ctx.relationalExpression().ret;
		if (a1.type instanceof VoidType || a2.type instanceof VoidType)
			throw new CompilationError("Cannot compare void");
		if ((a1.type instanceof StructOrUnionType) || (a2.type instanceof StructOrUnionType))
			throw new CompilationError("Cannot compare struct/union");
		Integer v1 = toInt(a1), v2 = toInt(a2);
		if (v1 != null && v2 != null) {
			ctx.ret = new IntConstant(v1.equals(v2) ? 1 : 0, new IntType(), false);
		} else {
			// Comparison between pointer and integer is allowed
			ctx.ret = new EqualTo(a1, a2, new IntType(), false);
		}
		return super.visitEqualityExpression2(ctx);
	}

	@Override
	public Object visitEqualityExpression3(@NotNull Compiler2015Parser.EqualityExpression3Context ctx) {
		visit(ctx.equalityExpression());
		visit(ctx.relationalExpression());
		Expression a1 = ctx.equalityExpression().ret;
		Expression a2 = ctx.relationalExpression().ret;
		if (a1.type instanceof VoidType || a2.type instanceof VoidType)
			throw new CompilationError("Cannot compare void");
		if ((a1.type instanceof StructOrUnionType) || (a2.type instanceof StructOrUnionType))
			throw new CompilationError("Cannot compare struct/union");
		Integer v1 = toInt(a1), v2 = toInt(a2);
		if (v1 != null && v2 != null) {
			ctx.ret = new IntConstant(!v1.equals(v2) ? 1 : 0, new IntType(), false);
		} else {
			// Comparison between pointer and integer is allowed
			ctx.ret = new NotEqualTo(a1, a2, new IntType(), false);
		}
		return super.visitEqualityExpression3(ctx);
	}

	@Override
	public Object visitAndExpression1(@NotNull Compiler2015Parser.AndExpression1Context ctx) {
		visit(ctx.equalityExpression());
		ctx.ret = ctx.equalityExpression().ret;
		return super.visitAndExpression1(ctx);
	}

	@Override
	public Object visitAndExpression2(@NotNull Compiler2015Parser.AndExpression2Context ctx) {
		visit(ctx.andExpression());
		visit(ctx.equalityExpression());
		Expression a1 = ctx.andExpression().ret;
		Expression a2 = ctx.equalityExpression().ret;
		if (!Type.isNumeric(a1.type) || !Type.isNumeric(a2.type))
			throw new CompilationError("& must be operated on numeric types");
		Integer v1 = toInt(a1), v2 = toInt(a2);
		if (v1 != null && v2 != null) {
			ctx.ret = new IntConstant(v1 & v2, new IntType(), false);
		} else {
			if (!(a1.type instanceof IntType))
				a1 = new CastExpression(new IntType(), a1, new IntType(), false);
			if (!(a2.type instanceof IntType))
				a2 = new CastExpression(new IntType(), a2, new IntType(), false);
			ctx.ret = new BitwiseAnd(a1, a2, new IntType(), false);
		}
		return super.visitAndExpression2(ctx);
	}

	@Override
	public Object visitExclusiveOrExpression1(@NotNull Compiler2015Parser.ExclusiveOrExpression1Context ctx) {
		visit(ctx.andExpression());
		ctx.ret = ctx.andExpression().ret;
		return super.visitExclusiveOrExpression1(ctx);
	}

	@Override
	public Object visitExclusiveOrExpression2(@NotNull Compiler2015Parser.ExclusiveOrExpression2Context ctx) {
		visit(ctx.exclusiveOrExpression());
		visit(ctx.andExpression());
		Expression a1 = ctx.exclusiveOrExpression().ret;
		Expression a2 = ctx.andExpression().ret;
		if (!Type.isNumeric(a1.type) || !Type.isNumeric(a2.type))
			throw new CompilationError("& must be operated on numeric types");
		Integer v1 = toInt(a1), v2 = toInt(a2);
		if (v1 != null && v2 != null) {
			ctx.ret = new IntConstant(v1 ^ v2, new IntType(), false);
		} else {
			if (!(a1.type instanceof IntType))
				a1 = new CastExpression(new IntType(), a1, new IntType(), false);
			if (!(a2.type instanceof IntType))
				a2 = new CastExpression(new IntType(), a2, new IntType(), false);
			ctx.ret = new BitwiseXOR(a1, a2, new IntType(), false);
		}
		return super.visitExclusiveOrExpression2(ctx);
	}

	@Override
	public Object visitInclusiveOrExpression1(@NotNull Compiler2015Parser.InclusiveOrExpression1Context ctx) {
		visit(ctx.exclusiveOrExpression());
		ctx.ret = ctx.exclusiveOrExpression().ret;
		return super.visitInclusiveOrExpression1(ctx);
	}

	@Override
	public Object visitInclusiveOrExpression2(@NotNull Compiler2015Parser.InclusiveOrExpression2Context ctx) {
		visit(ctx.inclusiveOrExpression());
		visit(ctx.exclusiveOrExpression());
		Expression a1 = ctx.inclusiveOrExpression().ret;
		Expression a2 = ctx.exclusiveOrExpression().ret;
		if (!Type.isNumeric(a1.type) || !Type.isNumeric(a2.type))
			throw new CompilationError("| must be operated on numeric types");
		Integer v1 = toInt(a1), v2 = toInt(a2);
		if (v1 != null && v2 != null) {
			ctx.ret = new IntConstant(v1 | v2, new IntType(), false);
		} else {
			if (!(a1.type instanceof IntType))
				a1 = new CastExpression(new IntType(), a1, new IntType(), false);
			if (!(a2.type instanceof IntType))
				a2 = new CastExpression(new IntType(), a2, new IntType(), false);
			ctx.ret = new BitwiseOr(a1, a2, new IntType(), false);
		}
		return super.visitInclusiveOrExpression2(ctx);
	}

	@Override
	public Object visitLogicalAndExpression1(@NotNull Compiler2015Parser.LogicalAndExpression1Context ctx) {
		visit(ctx.inclusiveOrExpression());
		ctx.ret = ctx.inclusiveOrExpression().ret;
		return super.visitLogicalAndExpression1(ctx);
	}

	@Override
	public Object visitLogicalAndExpression2(@NotNull Compiler2015Parser.LogicalAndExpression2Context ctx) {
		visit(ctx.logicalAndExpression());
		visit(ctx.inclusiveOrExpression());
		Expression a1 = ctx.logicalAndExpression().ret;
		Expression a2 = ctx.inclusiveOrExpression().ret;
		if (!Type.isNumeric(a1.type) || !Type.isNumeric(a2.type))
			throw new CompilationError("&& must be operated on numeric types");
		Integer v1 = toInt(a1), v2 = toInt(a2);
		if (v1 != null && v2 != null) {
			ctx.ret = new IntConstant(((v1 != 0) && (v2 != 0)) ? 1 : 0, new IntType(), false);
		} else {
			if (!(a1.type instanceof IntType))
				a1 = new CastExpression(new IntType(), a1, new IntType(), false);
			if (!(a2.type instanceof IntType))
				a2 = new CastExpression(new IntType(), a2, new IntType(), false);
			ctx.ret = new LogicalAnd(a1, a2, new IntType(), false);
		}
		return super.visitLogicalAndExpression2(ctx);
	}

	@Override
	public Object visitLogicalOrExpression1(@NotNull Compiler2015Parser.LogicalOrExpression1Context ctx) {
		visit(ctx.logicalAndExpression());
		ctx.ret = ctx.logicalAndExpression().ret;
		return super.visitLogicalOrExpression1(ctx);
	}

	@Override
	public Object visitLogicalOrExpression2(@NotNull Compiler2015Parser.LogicalOrExpression2Context ctx) {
		visit(ctx.logicalOrExpression());
		visit(ctx.logicalAndExpression());
		Expression a1 = ctx.logicalOrExpression().ret;
		Expression a2 = ctx.logicalAndExpression().ret;
		if (!Type.isNumeric(a1.type) || !Type.isNumeric(a2.type))
			throw new CompilationError("|| must be operated on numeric types");
		Integer v1 = toInt(a1), v2 = toInt(a2);
		if (v1 != null && v2 != null) {
			ctx.ret = new IntConstant(((v1 != 0) || (v2 != 0)) ? 1 : 0, new IntType(), false);
		} else {
			if (!(a1.type instanceof IntType))
				a1 = new CastExpression(new IntType(), a1, new IntType(), false);
			if (!(a2.type instanceof IntType))
				a2 = new CastExpression(new IntType(), a2, new IntType(), false);
			ctx.ret = new LogicalOr(a1, a2, new IntType(), false);
		}
		return super.visitLogicalOrExpression2(ctx);
	}

	@Override
	public Object visitConditionalExpression1(@NotNull Compiler2015Parser.ConditionalExpression1Context ctx) {
		visit(ctx.logicalOrExpression());
		ctx.ret = ctx.logicalOrExpression().ret;
		return super.visitConditionalExpression1(ctx);
	}

	@Override
	public Object visitConditionalExpression2(@NotNull Compiler2015Parser.ConditionalExpression2Context ctx) {
		visit(ctx.logicalOrExpression());
		visit(ctx.expression());
		visit(ctx.conditionalExpression());
		Expression a1 = ctx.logicalOrExpression().ret;
		Expression a2 = ctx.expression().ret;
		Expression a3 = ctx.conditionalExpression().ret;
		Integer v = toInt(a1);
		if (v != null) {
			if (v != 0)
				ctx.ret = a2;
			else
				ctx.ret = a3;
		} else {
			if (a2.type instanceof CharType && a3.type instanceof IntType)
				a2 = new CastExpression(new IntType(), a2, a2.type, false);
			if (a2.type instanceof IntType && a3.type instanceof CharType)
				a3 = new CastExpression(new IntType(), a3, a3.type, false);
			if (!Type.sameType(a2.type, a3.type))
				throw new CompilationError("Two expressions around : have different types.");
			ctx.ret = new ConditionalExpression(a1, a2, a3, a2.type, false);
		}
		return super.visitConditionalExpression2(ctx);
	}

	@Override
	public Object visitAssignmentExpression1(@NotNull Compiler2015Parser.AssignmentExpression1Context ctx) {
		visit(ctx.conditionalExpression());
		ctx.ret = ctx.conditionalExpression().ret;
		return super.visitAssignmentExpression1(ctx);
	}

	@Override
	public Object visitAssignmentExpression2(@NotNull Compiler2015Parser.AssignmentExpression2Context ctx) {
		visit(ctx.unaryExpression());
		visit(ctx.assignmentExpression());
		Expression a1 = ctx.unaryExpression().ret;
		Expression a2 = ctx.assignmentExpression().ret;
		if (!a1.isLValue)
			throw new CompilationError("Expression to the left of = is not left-value.");
		if (a1.type instanceof VoidType || a2.type instanceof VoidType)
			throw new CompilationError("Cannot operate on void.");
		if (a1.type instanceof StructOrUnionType && !Type.sameType(a1.type, a2.type))
			throw new CompilationError("Cannot assign different types to structures");
		if (a1.type instanceof IntType && a2.type instanceof CharType) {
			Integer v = toInt(a2);
			if (v != null)
				a2 = new IntConstant(v, new IntType(), false);
		}
		if (a1.type instanceof CharType && a2.type instanceof IntType) {
			Integer v = toInt(a2);
			if (v != null)
				a2 = new CharConstant((char) v.intValue(), new CharType(), false);
		}
		if (!Type.sameType(a1.type, a2.type))
			throw new CompilationError("Internal Error.");
		ctx.ret = new Assign(a1, a2, a1.type, true);
		return super.visitAssignmentExpression2(ctx);
	}

	@Override
	public Object visitAssignmentExpression3(@NotNull Compiler2015Parser.AssignmentExpression3Context ctx) {
		visit(ctx.unaryExpression());
		visit(ctx.assignmentExpression());
		Expression a1 = ctx.unaryExpression().ret;
		Expression a2 = ctx.assignmentExpression().ret;
		if (!a1.isLValue)
			throw new CompilationError("Expression to the left of = is not left-value.");
		if (a1.type instanceof VoidType || a2.type instanceof VoidType)
			throw new CompilationError("Cannot operate on void.");
		if (a1.type instanceof StructOrUnionType && !Type.sameType(a1.type, a2.type))
			throw new CompilationError("Cannot assign different types to structures");
		if (a1.type instanceof IntType && a2.type instanceof CharType) {
			Integer v = toInt(a2);
			if (v != null)
				a2 = new IntConstant(v, new IntType(), false);
		}
		if (a1.type instanceof CharType && a2.type instanceof IntType) {
			Integer v = toInt(a2);
			if (v != null)
				a2 = new CharConstant((char) v.intValue(), new CharType(), false);
		}
		if (!Type.sameType(a1.type, a2.type))
			throw new CompilationError("Internal Error.");
		ctx.ret = new MultiplyAssign(a1, a2, a1.type, true);
		return super.visitAssignmentExpression3(ctx);
	}

	@Override
	public Object visitAssignmentExpression4(@NotNull Compiler2015Parser.AssignmentExpression4Context ctx) {
		visit(ctx.unaryExpression());
		visit(ctx.assignmentExpression());
		Expression a1 = ctx.unaryExpression().ret;
		Expression a2 = ctx.assignmentExpression().ret;
		if (!a1.isLValue)
			throw new CompilationError("Expression to the left of = is not left-value.");
		if (a1.type instanceof VoidType || a2.type instanceof VoidType)
			throw new CompilationError("Cannot operate on void.");
		if (a1.type instanceof StructOrUnionType && !Type.sameType(a1.type, a2.type))
			throw new CompilationError("Cannot assign different types to structures");
		if (a1.type instanceof IntType && a2.type instanceof CharType) {
			Integer v = toInt(a2);
			if (v != null)
				a2 = new IntConstant(v, new IntType(), false);
		}
		if (a1.type instanceof CharType && a2.type instanceof IntType) {
			Integer v = toInt(a2);
			if (v != null)
				a2 = new CharConstant((char) v.intValue(), new CharType(), false);
		}
		if (!Type.sameType(a1.type, a2.type))
			throw new CompilationError("Internal Error.");
		ctx.ret = new DivideAssign(a1, a2, a1.type, true);
		return super.visitAssignmentExpression4(ctx);
	}

	@Override
	public Object visitAssignmentExpression5(@NotNull Compiler2015Parser.AssignmentExpression5Context ctx) {
		visit(ctx.unaryExpression());
		visit(ctx.assignmentExpression());
		Expression a1 = ctx.unaryExpression().ret;
		Expression a2 = ctx.assignmentExpression().ret;
		if (!a1.isLValue)
			throw new CompilationError("Expression to the left of = is not left-value.");
		if (a1.type instanceof VoidType || a2.type instanceof VoidType)
			throw new CompilationError("Cannot operate on void.");
		if (a1.type instanceof StructOrUnionType && !Type.sameType(a1.type, a2.type))
			throw new CompilationError("Cannot assign different types to structures");
		if (a1.type instanceof IntType && a2.type instanceof CharType) {
			Integer v = toInt(a2);
			if (v != null)
				a2 = new IntConstant(v, new IntType(), false);
		}
		if (a1.type instanceof CharType && a2.type instanceof IntType) {
			Integer v = toInt(a2);
			if (v != null)
				a2 = new CharConstant((char) v.intValue(), new CharType(), false);
		}
		if (!Type.sameType(a1.type, a2.type))
			throw new CompilationError("Internal Error.");
		ctx.ret = new ModuloAssign(a1, a2, a1.type, true);
		return super.visitAssignmentExpression5(ctx);
	}

	@Override
	public Object visitAssignmentExpression6(@NotNull Compiler2015Parser.AssignmentExpression6Context ctx) {
		visit(ctx.unaryExpression());
		visit(ctx.assignmentExpression());
		Expression a1 = ctx.unaryExpression().ret;
		Expression a2 = ctx.assignmentExpression().ret;
		if (!a1.isLValue)
			throw new CompilationError("Expression to the left of = is not left-value.");
		if (a1.type instanceof VoidType || a2.type instanceof VoidType)
			throw new CompilationError("Cannot operate on void.");
		if (a1.type instanceof StructOrUnionType && !Type.sameType(a1.type, a2.type))
			throw new CompilationError("Cannot assign different types to structures");
		if (a1.type instanceof IntType && a2.type instanceof CharType) {
			Integer v = toInt(a2);
			if (v != null)
				a2 = new IntConstant(v, new IntType(), false);
		}
		if (a1.type instanceof CharType && a2.type instanceof IntType) {
			Integer v = toInt(a2);
			if (v != null)
				a2 = new CharConstant((char) v.intValue(), new CharType(), false);
		}
		if (!Type.sameType(a1.type, a2.type))
			throw new CompilationError("Internal Error.");
		ctx.ret = new AddAssign(a1, a2, a1.type, true);
		return super.visitAssignmentExpression6(ctx);
	}

	@Override
	public Object visitAssignmentExpression7(@NotNull Compiler2015Parser.AssignmentExpression7Context ctx) {
		visit(ctx.unaryExpression());
		visit(ctx.assignmentExpression());
		Expression a1 = ctx.unaryExpression().ret;
		Expression a2 = ctx.assignmentExpression().ret;
		if (!a1.isLValue)
			throw new CompilationError("Expression to the left of = is not left-value.");
		if (a1.type instanceof VoidType || a2.type instanceof VoidType)
			throw new CompilationError("Cannot operate on void.");
		if (a1.type instanceof StructOrUnionType && !Type.sameType(a1.type, a2.type))
			throw new CompilationError("Cannot assign different types to structures");
		if (a1.type instanceof IntType && a2.type instanceof CharType) {
			Integer v = toInt(a2);
			if (v != null)
				a2 = new IntConstant(v, new IntType(), false);
		}
		if (a1.type instanceof CharType && a2.type instanceof IntType) {
			Integer v = toInt(a2);
			if (v != null)
				a2 = new CharConstant((char) v.intValue(), new CharType(), false);
		}
		if (!Type.sameType(a1.type, a2.type))
			throw new CompilationError("Internal Error.");
		ctx.ret = new SubtractAssign(a1, a2, a1.type, true);
		return super.visitAssignmentExpression7(ctx);
	}

	@Override
	public Object visitAssignmentExpression8(@NotNull Compiler2015Parser.AssignmentExpression8Context ctx) {
		visit(ctx.unaryExpression());
		visit(ctx.assignmentExpression());
		Expression a1 = ctx.unaryExpression().ret;
		Expression a2 = ctx.assignmentExpression().ret;
		if (!a1.isLValue)
			throw new CompilationError("Expression to the left of = is not left-value.");
		if (a1.type instanceof VoidType || a2.type instanceof VoidType)
			throw new CompilationError("Cannot operate on void.");
		if (a1.type instanceof StructOrUnionType && !Type.sameType(a1.type, a2.type))
			throw new CompilationError("Cannot assign different types to structures");
		if (a1.type instanceof IntType && a2.type instanceof CharType) {
			Integer v = toInt(a2);
			if (v != null)
				a2 = new IntConstant(v, new IntType(), false);
		}
		if (a1.type instanceof CharType && a2.type instanceof IntType) {
			Integer v = toInt(a2);
			if (v != null)
				a2 = new CharConstant((char) v.intValue(), new CharType(), false);
		}
		if (!Type.sameType(a1.type, a2.type))
			throw new CompilationError("Internal Error.");
		ctx.ret = new ShiftLeftAssign(a1, a2, a1.type, true);
		return super.visitAssignmentExpression8(ctx);
	}

	@Override
	public Object visitAssignmentExpression9(@NotNull Compiler2015Parser.AssignmentExpression9Context ctx) {
		visit(ctx.unaryExpression());
		visit(ctx.assignmentExpression());
		Expression a1 = ctx.unaryExpression().ret;
		Expression a2 = ctx.assignmentExpression().ret;
		if (!a1.isLValue)
			throw new CompilationError("Expression to the left of = is not left-value.");
		if (a1.type instanceof VoidType || a2.type instanceof VoidType)
			throw new CompilationError("Cannot operate on void.");
		if (a1.type instanceof StructOrUnionType && !Type.sameType(a1.type, a2.type))
			throw new CompilationError("Cannot assign different types to structures");
		if (a1.type instanceof IntType && a2.type instanceof CharType) {
			Integer v = toInt(a2);
			if (v != null)
				a2 = new IntConstant(v, new IntType(), false);
		}
		if (a1.type instanceof CharType && a2.type instanceof IntType) {
			Integer v = toInt(a2);
			if (v != null)
				a2 = new CharConstant((char) v.intValue(), new CharType(), false);
		}
		if (!Type.sameType(a1.type, a2.type))
			throw new CompilationError("Internal Error.");
		ctx.ret = new ShiftRightAssign(a1, a2, a1.type, true);
		return super.visitAssignmentExpression9(ctx);
	}

	@Override
	public Object visitAssignmentExpression10(@NotNull Compiler2015Parser.AssignmentExpression10Context ctx) {
		visit(ctx.unaryExpression());
		visit(ctx.assignmentExpression());
		Expression a1 = ctx.unaryExpression().ret;
		Expression a2 = ctx.assignmentExpression().ret;
		if (!a1.isLValue)
			throw new CompilationError("Expression to the left of = is not left-value.");
		if (a1.type instanceof VoidType || a2.type instanceof VoidType)
			throw new CompilationError("Cannot operate on void.");
		if (a1.type instanceof StructOrUnionType && !Type.sameType(a1.type, a2.type))
			throw new CompilationError("Cannot assign different types to structures");
		if (a1.type instanceof IntType && a2.type instanceof CharType) {
			Integer v = toInt(a2);
			if (v != null)
				a2 = new IntConstant(v, new IntType(), false);
		}
		if (a1.type instanceof CharType && a2.type instanceof IntType) {
			Integer v = toInt(a2);
			if (v != null)
				a2 = new CharConstant((char) v.intValue(), new CharType(), false);
		}
		if (!Type.sameType(a1.type, a2.type))
			throw new CompilationError("Internal Error.");
		ctx.ret = new BitwiseAndAssign(a1, a2, a1.type, true);
		return super.visitAssignmentExpression10(ctx);
	}

	@Override
	public Object visitAssignmentExpression11(@NotNull Compiler2015Parser.AssignmentExpression11Context ctx) {
		visit(ctx.unaryExpression());
		visit(ctx.assignmentExpression());
		Expression a1 = ctx.unaryExpression().ret;
		Expression a2 = ctx.assignmentExpression().ret;
		if (!a1.isLValue)
			throw new CompilationError("Expression to the left of = is not left-value.");
		if (a1.type instanceof VoidType || a2.type instanceof VoidType)
			throw new CompilationError("Cannot operate on void.");
		if (a1.type instanceof StructOrUnionType && !Type.sameType(a1.type, a2.type))
			throw new CompilationError("Cannot assign different types to structures");
		if (a1.type instanceof IntType && a2.type instanceof CharType) {
			Integer v = toInt(a2);
			if (v != null)
				a2 = new IntConstant(v, new IntType(), false);
		}
		if (a1.type instanceof CharType && a2.type instanceof IntType) {
			Integer v = toInt(a2);
			if (v != null)
				a2 = new CharConstant((char) v.intValue(), new CharType(), false);
		}
		if (!Type.sameType(a1.type, a2.type))
			throw new CompilationError("Internal Error.");
		ctx.ret = new BitwiseXORAssign(a1, a2, a1.type, true);
		return super.visitAssignmentExpression11(ctx);
	}

	@Override
	public Object visitAssignmentExpression12(@NotNull Compiler2015Parser.AssignmentExpression12Context ctx) {
		visit(ctx.unaryExpression());
		visit(ctx.assignmentExpression());
		Expression a1 = ctx.unaryExpression().ret;
		Expression a2 = ctx.assignmentExpression().ret;
		if (!a1.isLValue)
			throw new CompilationError("Expression to the left of = is not left-value.");
		if (a1.type instanceof VoidType || a2.type instanceof VoidType)
			throw new CompilationError("Cannot operate on void.");
		if (a1.type instanceof StructOrUnionType && !Type.sameType(a1.type, a2.type))
			throw new CompilationError("Cannot assign different types to structures");
		if (a1.type instanceof IntType && a2.type instanceof CharType) {
			Integer v = toInt(a2);
			if (v != null)
				a2 = new IntConstant(v, new IntType(), false);
		}
		if (a1.type instanceof CharType && a2.type instanceof IntType) {
			Integer v = toInt(a2);
			if (v != null)
				a2 = new CharConstant((char) v.intValue(), new CharType(), false);
		}
		if (!Type.sameType(a1.type, a2.type))
			throw new CompilationError("Internal Error.");
		ctx.ret = new BitwiseOrAssign(a1, a2, a1.type, true);
		return super.visitAssignmentExpression12(ctx);
	}

	@Override
	public Object visitExpression1(@NotNull Compiler2015Parser.Expression1Context ctx) {
		visit(ctx.assignmentExpression());
		ctx.ret = ctx.assignmentExpression().ret;
		return super.visitExpression1(ctx);
	}

	@Override
	public Object visitExpression2(@NotNull Compiler2015Parser.Expression2Context ctx) {
		visit(ctx.expression());
		visit(ctx.assignmentExpression());
		Expression e1 = ctx.expression().ret;
		Expression e2 = ctx.assignmentExpression().ret;
		Integer v1 = toInt(e1), v2 = toInt(e2);
		if (v1 != null && v2 != null)
			ctx.ret = new IntConstant(v1, new IntType(), false);
		else
			ctx.ret = new CommaExpression(e1, e2, e2.type, e2.isLValue);
		return super.visitExpression2(ctx);
	}

	@Override
	public Object visitDeclaration1(@NotNull Compiler2015Parser.Declaration1Context ctx) {
		return super.visitDeclaration1(ctx);
	}

	@Override
	public Object visitDeclaration2(@NotNull Compiler2015Parser.Declaration2Context ctx) {
		return super.visitDeclaration2(ctx);
	}

	@Override
	public Object visitInitDeclaratorList1(@NotNull Compiler2015Parser.InitDeclaratorList1Context ctx) {
		return super.visitInitDeclaratorList1(ctx);
	}

	@Override
	public Object visitInitDeclarator1(@NotNull Compiler2015Parser.InitDeclarator1Context ctx) {
		return super.visitInitDeclarator1(ctx);
	}

	@Override
	public Object visitInitDeclarator2(@NotNull Compiler2015Parser.InitDeclarator2Context ctx) {
		return super.visitInitDeclarator2(ctx);
	}

	@Override
	public Object visitInitDeclaratorList2(@NotNull Compiler2015Parser.InitDeclaratorList2Context ctx) {
		return super.visitInitDeclaratorList2(ctx);
	}

	@Override
	public Object visitTypeSpecifier1(@NotNull Compiler2015Parser.TypeSpecifier1Context ctx) {
		ctx.ret = new VoidType();
		return super.visitTypeSpecifier1(ctx);
	}

	@Override
	public Object visitTypeSpecifier2(@NotNull Compiler2015Parser.TypeSpecifier2Context ctx) {
		ctx.ret = new CharType();
		return super.visitTypeSpecifier2(ctx);
	}

	@Override
	public Object visitTypeSpecifier3(@NotNull Compiler2015Parser.TypeSpecifier3Context ctx) {
		ctx.ret = new IntType();
		return super.visitTypeSpecifier3(ctx);
	}

	@Override
	public Object visitTypeSpecifier4(@NotNull Compiler2015Parser.TypeSpecifier4Context ctx) {
		visit(ctx.structOrUnionSpecifier());
		ctx.ret = ctx.structOrUnionSpecifier().ret;
		return super.visitTypeSpecifier4(ctx);
	}

	@Override
	public Object visitTypeSpecifier5(@NotNull Compiler2015Parser.TypeSpecifier5Context ctx) {
		visit(ctx.typedefName());
		String name = ctx.typedefName().getText();
		ctx.ret = (Type) Environment.symbolNames.queryName(name).ref;
		return super.visitTypeSpecifier5(ctx);
	}

	@Override
	public Object visitStructOrUnionSpecifier1(@NotNull Compiler2015Parser.StructOrUnionSpecifier1Context ctx) {
		return super.visitStructOrUnionSpecifier1(ctx);
	}

	@Override
	public Object visitStructOrUnionSpecifier2(@NotNull Compiler2015Parser.StructOrUnionSpecifier2Context ctx) {
		return super.visitStructOrUnionSpecifier2(ctx);
	}

	@Override
	public Object visitStructDeclaration1(@NotNull Compiler2015Parser.StructDeclaration1Context ctx) {
		return super.visitStructDeclaration1(ctx);
	}

	@Override
	public Object visitStructDeclaration2(@NotNull Compiler2015Parser.StructDeclaration2Context ctx) {
		return super.visitStructDeclaration2(ctx);
	}

	@Override
	public Object visitStructDeclaratorList(@NotNull Compiler2015Parser.StructDeclaratorListContext ctx) {
		ctx.ret = new ArrayList<ASTNode>();
		for (Compiler2015Parser.DeclaratorContext x : ctx.declarator()) {
			visit(x);
			if (x.ret != null)
				ctx.ret.add(x.ret);
		}
		return super.visitStructDeclaratorList(ctx);
	}

	@Override
	public Object visitDeclarator(@NotNull Compiler2015Parser.DeclaratorContext ctx) {
		return super.visitDeclarator(ctx);
	}

	@Override
	public Object visitDirectDeclarator1(@NotNull Compiler2015Parser.DirectDeclarator1Context ctx) {
		return super.visitDirectDeclarator1(ctx);
	}

	@Override
	public Object visitDirectDeclarator2(@NotNull Compiler2015Parser.DirectDeclarator2Context ctx) {
		return super.visitDirectDeclarator2(ctx);
	}

	@Override
	public Object visitDirectDeclarator3(@NotNull Compiler2015Parser.DirectDeclarator3Context ctx) {
		return super.visitDirectDeclarator3(ctx);
	}

	@Override
	public Object visitDirectDeclarator4(@NotNull Compiler2015Parser.DirectDeclarator4Context ctx) {
		return super.visitDirectDeclarator4(ctx);
	}

	@Override
	public Object visitParameterTypeList1(@NotNull Compiler2015Parser.ParameterTypeList1Context ctx) {
		return super.visitParameterTypeList1(ctx);
	}

	@Override
	public Object visitParameterTypeList2(@NotNull Compiler2015Parser.ParameterTypeList2Context ctx) {
		return super.visitParameterTypeList2(ctx);
	}

	@Override
	public Object visitParameterList1(@NotNull Compiler2015Parser.ParameterList1Context ctx) {
		return super.visitParameterList1(ctx);
	}

	@Override
	public Object visitParameterList2(@NotNull Compiler2015Parser.ParameterList2Context ctx) {
		return super.visitParameterList2(ctx);
	}

	@Override
	public Object visitParameterDeclaration1(@NotNull Compiler2015Parser.ParameterDeclaration1Context ctx) {
		return super.visitParameterDeclaration1(ctx);
	}

	@Override
	public Object visitParameterDeclaration2(@NotNull Compiler2015Parser.ParameterDeclaration2Context ctx) {
		return super.visitParameterDeclaration2(ctx);
	}

	@Override
	public Object visitTypeName(@NotNull Compiler2015Parser.TypeNameContext ctx) {
		return super.visitTypeName(ctx);
	}

	@Override
	public Object visitAbstractDeclarator1(@NotNull Compiler2015Parser.AbstractDeclarator1Context ctx) {
		return super.visitAbstractDeclarator1(ctx);
	}

	@Override
	public Object visitAbstractDeclarator2(@NotNull Compiler2015Parser.AbstractDeclarator2Context ctx) {
		return super.visitAbstractDeclarator2(ctx);
	}

	@Override
	public Object visitDirectAbstractDeclarator1(@NotNull Compiler2015Parser.DirectAbstractDeclarator1Context ctx) {
		return super.visitDirectAbstractDeclarator1(ctx);
	}

	@Override
	public Object visitDirectAbstractDeclarator2(@NotNull Compiler2015Parser.DirectAbstractDeclarator2Context ctx) {
		return super.visitDirectAbstractDeclarator2(ctx);
	}

	@Override
	public Object visitDirectAbstractDeclarator3(@NotNull Compiler2015Parser.DirectAbstractDeclarator3Context ctx) {
		return super.visitDirectAbstractDeclarator3(ctx);
	}

	@Override
	public Object visitDirectAbstractDeclarator4(@NotNull Compiler2015Parser.DirectAbstractDeclarator4Context ctx) {
		return super.visitDirectAbstractDeclarator4(ctx);
	}

	@Override
	public Object visitDirectAbstractDeclarator5(@NotNull Compiler2015Parser.DirectAbstractDeclarator5Context ctx) {
		return super.visitDirectAbstractDeclarator5(ctx);
	}

	@Override
	public Object visitTypedefName(@NotNull Compiler2015Parser.TypedefNameContext ctx) {
		String name = ctx.Identifier().getText();
		ctx.ret = (Type) Environment.symbolNames.queryName(name).ref;
		return super.visitTypedefName(ctx);
	}

	@Override
	public Object visitInitializer1(@NotNull Compiler2015Parser.Initializer1Context ctx) {
		return super.visitInitializer1(ctx);
	}

	@Override
	public Object visitInitializer2(@NotNull Compiler2015Parser.Initializer2Context ctx) {
		return super.visitInitializer2(ctx);
	}

	@Override
	public Object visitInitializer3(@NotNull Compiler2015Parser.Initializer3Context ctx) {
		return super.visitInitializer3(ctx);
	}

	@Override
	public Object visitInitializerList(@NotNull Compiler2015Parser.InitializerListContext ctx) {
		return super.visitInitializerList(ctx);
	}

	@Override
	public Object visitCompoundStatement(@NotNull Compiler2015Parser.CompoundStatementContext ctx) {
		ArrayList<ASTNode> ret = new ArrayList<ASTNode>();
		for (Compiler2015Parser.BlockItemContext x : ctx.blockItem()) {
			visit(x);
			if (x.ret != null)
				ret.add(x.ret);
		}
		ctx.ret = new CompoundStatement(ret);
		return super.visitCompoundStatement(ctx);
	}

	@Override
	public Object visitBlockItem1(@NotNull Compiler2015Parser.BlockItem1Context ctx) {
		visit(ctx.declaration());
		ctx.ret = ctx.declaration().ret;
		return super.visitBlockItem1(ctx);
	}

	@Override
	public Object visitBlockItem2(@NotNull Compiler2015Parser.BlockItem2Context ctx) {
		visit(ctx.functionDefinition());
		ctx.ret = ctx.functionDefinition().ret;
		return super.visitBlockItem2(ctx);
	}

	@Override
	public Object visitBlockItem3(@NotNull Compiler2015Parser.BlockItem3Context ctx) {
		visit(ctx.statement());
		ctx.ret = ctx.statement().ret;
		return super.visitBlockItem3(ctx);
	}

	@Override
	public Object visitExpressionStatement(@NotNull Compiler2015Parser.ExpressionStatementContext ctx) {
		return super.visitExpressionStatement(ctx);
	}

	@Override
	public Object visitSelectionStatement(@NotNull Compiler2015Parser.SelectionStatementContext ctx) {
		return super.visitSelectionStatement(ctx);
	}

	@Override
	public Object visitIterationStatement1(@NotNull Compiler2015Parser.IterationStatement1Context ctx) {
		visit(ctx.expression());
		visit(ctx.statement());
		Expression e = ctx.expression().ret;
		Statement s = ctx.statement().ret;
		ctx.ret = new WhileStatement(e, s);
		return super.visitIterationStatement1(ctx);
	}

	@Override
	public Object visitIterationStatement2(@NotNull Compiler2015Parser.IterationStatement2Context ctx) {
		visit(ctx.E1);
		visit(ctx.E2);
		visit(ctx.E3);
		visit(ctx.statement());
		Expression e1 = ctx.E1.ret, e2 = ctx.E2.ret, e3 = ctx.E3.ret;
		Statement s = ctx.statement().ret;
		ctx.ret = new ForStatement(e1, e2, e3, s);
		return super.visitIterationStatement2(ctx);
	}

	@Override
	public Object visitJumpStatement1(@NotNull Compiler2015Parser.JumpStatement1Context ctx) {
		ctx.ret = new ContinueStatement();
		return super.visitJumpStatement1(ctx);
	}

	@Override
	public Object visitJumpStatement2(@NotNull Compiler2015Parser.JumpStatement2Context ctx) {
		ctx.ret = new BreakStatement();
		return super.visitJumpStatement2(ctx);
	}

	@Override
	public Object visitJumpStatement3(@NotNull Compiler2015Parser.JumpStatement3Context ctx) {
		visit(ctx.expression());
		Expression e = ctx.expression() == null ? null : ctx.expression().ret;
		ctx.ret = new ReturnStatement(e);
		return super.visitJumpStatement3(ctx);
	}

	@Override
	public Object visitCompilationUnit(@NotNull Compiler2015Parser.CompilationUnitContext ctx) {
		ArrayList<ASTNode> children = new ArrayList<ASTNode>();
		for (Compiler2015Parser.ExternalDeclarationContext x : ctx.externalDeclaration()) {
			visit(x);
			if (x.ret != null)
				children.add(x.ret);
		}
		ctx.ret = new ASTRoot(children);
		return super.visitCompilationUnit(ctx);
	}

	@Override
	public Object visitFunctionDefinition(@NotNull Compiler2015Parser.FunctionDefinitionContext ctx) {
		return super.visitFunctionDefinition(ctx);
	}

	@Override
	public Object visitConstant1(@NotNull Compiler2015Parser.Constant1Context ctx) {
		ctx.ret = new IntConstant(
				Integer.parseInt(ctx.DecimalConstant().getText(), 10),
				new IntType(),
				false
		);
		return super.visitConstant1(ctx);
	}

	@Override
	public Object visitConstant2(@NotNull Compiler2015Parser.Constant2Context ctx) {
		ctx.ret = new IntConstant(
				Integer.parseInt(ctx.OctalConstant().getText(), 8),
				new IntType(),
				false
		);
		return super.visitConstant2(ctx);
	}

	@Override
	public Object visitConstant3(@NotNull Compiler2015Parser.Constant3Context ctx) {
		ctx.ret = new IntConstant(
				Integer.parseInt(ctx.HexadecimalConstant().getText(), 16),
				new IntType(),
				false
		);
		return super.visitConstant3(ctx);
	}

	@Override
	public Object visitConstant4(@NotNull Compiler2015Parser.Constant4Context ctx) {
		// TODO: error should occur if this is an Escape Character.
		ctx.ret = new CharConstant(
				ctx.CharacterConstant().getText().charAt(1),
				new CharType(),
				false
		);
		return super.visitConstant4(ctx);
	}

}

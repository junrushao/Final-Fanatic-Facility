package Compiler2015.Parser;

import Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression.*;
import Compiler2015.AST.Statement.ExpressionStatement.*;
import Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression.*;
import Compiler2015.AST.Type.*;
import Compiler2015.Exception.CompilationError;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;

public class MyVisitor extends Compiler2015BaseVisitor<Object> {

	@Override
	public Object visitPrimaryExpression1(@NotNull Compiler2015Parser.PrimaryExpression1Context ctx) {
		ctx.ret = new Identifier(ctx.Identifier().getText(), new IntType(), true);
		return super.visitPrimaryExpression1(ctx);
	}

	@Override
	public Object visitPrimaryExpression2(@NotNull Compiler2015Parser.PrimaryExpression2Context ctx) {
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
		ctx.ret = new StringConstant(sb.toString(), new ArrayPointerType(new CharType(), sb.length()), false);
		return super.visitPrimaryExpression3(ctx);
	}

	@Override
	public Object visitPrimaryExpression4(@NotNull Compiler2015Parser.PrimaryExpression4Context ctx) {
		ctx.ret = ctx.expression().ret;
		return super.visitPrimaryExpression4(ctx);
	}

	@Override
	public Object visitPostfixExpression1(@NotNull Compiler2015Parser.PostfixExpression1Context ctx) {
		ctx.ret = ctx.primaryExpression().ret;
		return super.visitPostfixExpression1(ctx);
	}

	@Override
	public Object visitPostfixExpression2(@NotNull Compiler2015Parser.PostfixExpression2Context ctx) {
		Type lType = ctx.postfixExpression().ret.type;
		ctx.ret = new ArrayAccess(
				ctx.postfixExpression().ret,
				ctx.expression().ret,
				Pointer.getPointTo(lType), // type-checking within this function
				true);
		return super.visitPostfixExpression2(ctx);
	}

	@Override
	public Object visitPostfixExpression3(@NotNull Compiler2015Parser.PostfixExpression3Context ctx) {
		Type lType = ctx.postfixExpression().ret.type;
		if (!(lType instanceof FunctionPointerType))
			throw new CompilationError("Not a pointer to function");
		FunctionPointerType l = (FunctionPointerType)lType;
		int size = l.ref.parameterTypes.length;
		if ((size == 0) != (ctx.argumentExpressionList() == null)) {
			throw new CompilationError("Parameter number unmatch");
		}
		ArrayList<Expression> list = ctx.argumentExpressionList().ret;
		if (size != list.size()) {
			throw new CompilationError("Parameter number unmatch");
		}
		for (int i = 0; i < size; ++i) {
			if (!Type.suitable(l.ref.parameterTypes[i], list.get(i).type))
				throw new CompilationError("Parameter type not matched");
		}
		Expression argList[] = new Expression[size];
		for (int i = 0; i < size; ++i)
			argList[i] = list.get(i);
		ctx.ret = new FunctionCall(l, argList, l.ref.returnType, false);
		return super.visitPostfixExpression3(ctx);
	}

	@Override
	public Object visitPostfixExpression4(@NotNull Compiler2015Parser.PostfixExpression4Context ctx) {
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
		Expression a1 = ctx.postfixExpression().ret;
		if (!a1.isLValue)
			throw new CompilationError("Not LValue.");
		if (a1.type instanceof VoidType || a1.type instanceof StructOrUnionType)
			throw new CompilationError("Such type supports no self-increment.");
		ctx.ret = new PostfixSelfInc(a1, a1.type, false);
		return super.visitPostfixExpression6(ctx);
	}

	@Override
	public Object visitPostfixExpression7(@NotNull Compiler2015Parser.PostfixExpression7Context ctx) {
		Expression a1 = ctx.postfixExpression().ret;
		if (!a1.isLValue)
			throw new CompilationError("Not LValue.");
		if (a1.type instanceof VoidType || a1.type instanceof StructOrUnionType)
			throw new CompilationError("Such type supports no self-decrement.");
		ctx.ret = new PostfixSelfDec(a1, a1.type, false);
		return super.visitPostfixExpression7(ctx);
	}

	@Override
	public Object visitArgumentExpressionList1(@NotNull final Compiler2015Parser.ArgumentExpressionList1Context ctx) {
		ctx.ret = new ArrayList<Expression>() {{add(ctx.assignmentExpression().ret);}};
		return super.visitArgumentExpressionList1(ctx);
	}

	@Override
	public Object visitArgumentExpressionList2(@NotNull Compiler2015Parser.ArgumentExpressionList2Context ctx) {
		ctx.ret = ctx.argumentExpressionList().ret;
		ctx.ret.add(ctx.assignmentExpression().ret);
		return super.visitArgumentExpressionList2(ctx);
	}

	@Override
	public Object visitUnaryExpression1(@NotNull Compiler2015Parser.UnaryExpression1Context ctx) {
		ctx.ret = ctx.postfixExpression().ret;
		return super.visitUnaryExpression1(ctx);
	}

	@Override
	public Object visitUnaryExpression2(@NotNull Compiler2015Parser.UnaryExpression2Context ctx) {
		Expression a1 = ctx.unaryExpression().ret;
		if (!a1.isLValue)
			throw new CompilationError("Not LValue.");
		if (a1.type instanceof VoidType || a1.type instanceof StructOrUnionType)
			throw new CompilationError("Such type supports no self-increment.");
		ctx.ret = new PrefixSelfInc(a1, a1.type, false);
		return super.visitUnaryExpression2(ctx);
	}


	@Override
	public Object visitUnaryExpression3(@NotNull Compiler2015Parser.UnaryExpression3Context ctx) {
		Expression a1 = ctx.unaryExpression().ret;
		if (!a1.isLValue)
			throw new CompilationError("Not LValue.");
		if (a1.type instanceof VoidType || a1.type instanceof StructOrUnionType)
			throw new CompilationError("Such type supports no self-decrement.");
		ctx.ret = new PrefixSelfDec(a1, a1.type, false);
		return super.visitUnaryExpression3(ctx);
	}

	@Override
	public Object visitUnaryExpression4(@NotNull Compiler2015Parser.UnaryExpression4Context ctx) {
		Expression e = ctx.castExpression().ret;
		if (!e.isLValue)
			throw new CompilationError("Not LValue.");
		ctx.ret = new AddressAccess(e, new VariablePointerType(e.type), false);
		return super.visitUnaryExpression4(ctx);
	}

	@Override
	public Object visitUnaryExpression5(@NotNull Compiler2015Parser.UnaryExpression5Context ctx) {
		Expression e = ctx.castExpression().ret;
		ctx.ret = new PointTo(e, Pointer.getPointTo(e.type), true);
		return super.visitUnaryExpression5(ctx);
	}

	@Override
	public Object visitUnaryExpression6(@NotNull Compiler2015Parser.UnaryExpression6Context ctx) {
		Expression e = ctx.castExpression().ret;
		if (!Type.isNumeric(e.type))
			throw new CompilationError("Cannot put unary plus before this type");
		ctx.ret = new Positive(e, e.type, false);
		return super.visitUnaryExpression6(ctx);
	}

	@Override
	public Object visitUnaryExpression7(@NotNull Compiler2015Parser.UnaryExpression7Context ctx) {
		Expression e = ctx.castExpression().ret;
		if (!Type.isNumeric(e.type))
			throw new CompilationError("Cannot put unary plus before this type");
		ctx.ret = new Negative(e, e.type, false);
		return super.visitUnaryExpression7(ctx);
	}

	@Override
	public Object visitUnaryExpression8(@NotNull Compiler2015Parser.UnaryExpression8Context ctx) {
		Expression e = ctx.castExpression().ret;
		if (!Type.isNumeric(e.type))
			throw new CompilationError("Cannot put unary plus before this type");
		ctx.ret = new BitwiseNot(e, e.type, false);
		return super.visitUnaryExpression8(ctx);
	}

	@Override
	public Object visitUnaryExpression9(@NotNull Compiler2015Parser.UnaryExpression9Context ctx) {
		Expression e = ctx.castExpression().ret;
		if (!Type.isNumeric(e.type))
			throw new CompilationError("Cannot put unary plus before this type");
		ctx.ret = new LogicalNot(e, e.type, false);
		return super.visitUnaryExpression9(ctx);
	}

	@Override
	public Object visitUnaryExpression10(@NotNull Compiler2015Parser.UnaryExpression10Context ctx) {
		Expression e = ctx.unaryExpression().ret;
		ctx.ret = new Sizeof(e, new IntType(), false);
		return super.visitUnaryExpression10(ctx);
	}

	@Override
	public Object visitUnaryExpression11(@NotNull Compiler2015Parser.UnaryExpression11Context ctx) {
		ctx.ret = new IntConstant(ctx.ret.sizeof(), new IntType(), false);
		return super.visitUnaryExpression11(ctx);
	}

	@Override
	public Object visitCastExpression1(@NotNull Compiler2015Parser.CastExpression1Context ctx) {
		ctx.ret = ctx.unaryExpression().ret;
		return super.visitCastExpression1(ctx);
	}

	@Override
	public Object visitCastExpression2(@NotNull Compiler2015Parser.CastExpression2Context ctx) {
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
			ctx.ret = new CastExpression(t, c, t, false);
		}
		return super.visitCastExpression2(ctx);
	}

	@Override
	public Object visitMultiplicativeExpression1(@NotNull Compiler2015Parser.MultiplicativeExpression1Context ctx) {
		ctx.ret = ctx.castExpression().ret;
		return super.visitMultiplicativeExpression1(ctx);
	}

	@Override
	public Object visitMultiplicativeExpression2(@NotNull Compiler2015Parser.MultiplicativeExpression2Context ctx) {
		Expression a1 = ctx.multiplicativeExpression().ret;
		Expression a2 = ctx.castExpression().ret;
		if (!Type.isNumeric(a1.type) || !Type.isNumeric(a2.type))
			throw new CompilationError("This type does not support numeric operation");
		if (a1.type instanceof CharType && a2.type instanceof IntType)
			a1 = new CastExpression(new IntType(), a1, new IntType(), false);
		else if (a1.type instanceof IntType && a2.type instanceof CharType)
			a2 = new CastExpression(new IntType(), a2, new IntType(), false);
		ctx.ret = new Multiply(a1, a2, a1.type, false);
		return super.visitMultiplicativeExpression2(ctx);
	}

	@Override
	public Object visitMultiplicativeExpression3(@NotNull Compiler2015Parser.MultiplicativeExpression3Context ctx) {
		Expression a1 = ctx.multiplicativeExpression().ret;
		Expression a2 = ctx.castExpression().ret;
		if (!Type.isNumeric(a1.type) || !Type.isNumeric(a2.type))
			throw new CompilationError("This type does not support numeric operation");
		if (a1.type instanceof CharType && a2.type instanceof IntType)
			a1 = new CastExpression(new IntType(), a1, new IntType(), false);
		else if (a1.type instanceof IntType && a2.type instanceof CharType)
			a2 = new CastExpression(new IntType(), a2, new IntType(), false);
		ctx.ret = new Divide(a1, a2, a1.type, false);
		return super.visitMultiplicativeExpression3(ctx);
	}

	@Override
	public Object visitMultiplicativeExpression4(@NotNull Compiler2015Parser.MultiplicativeExpression4Context ctx) {
		Expression a1 = ctx.multiplicativeExpression().ret;
		Expression a2 = ctx.castExpression().ret;
		if (!Type.isNumeric(a1.type) || !Type.isNumeric(a2.type))
			throw new CompilationError("This type does not support numeric operation");
		if (a1.type instanceof CharType && a2.type instanceof IntType)
			a1 = new CastExpression(new IntType(), a1, new IntType(), false);
		else if (a1.type instanceof IntType && a2.type instanceof CharType)
			a2 = new CastExpression(new IntType(), a2, new IntType(), false);
		ctx.ret = new Modulo(a1, a2, a1.type, false);
		return super.visitMultiplicativeExpression4(ctx);
	}

	@Override
	public Object visitAdditiveExpression1(@NotNull Compiler2015Parser.AdditiveExpression1Context ctx) {
		ctx.ret = ctx.multiplicativeExpression().ret;
		return super.visitAdditiveExpression1(ctx);
	}

	@Override
	public Object visitAdditiveExpression2(@NotNull Compiler2015Parser.AdditiveExpression2Context ctx) {
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
		if (a1.type instanceof CharType && a2.type instanceof IntType)
			a1 = new CastExpression(new IntType(), a1, new IntType(), false);
		else if (a1.type instanceof IntType && a2.type instanceof CharType)
			a2 = new CastExpression(new IntType(), a2, new IntType(), false);
		ctx.ret = new Add(a1, a2, (a2.type instanceof Pointer) ? a2.type : a1.type, false);
		return super.visitAdditiveExpression2(ctx);
	}

	@Override
	public Object visitAdditiveExpression3(@NotNull Compiler2015Parser.AdditiveExpression3Context ctx) {
		Expression a1 = ctx.additiveExpression().ret;
		Expression a2 = ctx.multiplicativeExpression().ret;
		if (a1.type instanceof VoidType || a2.type instanceof VoidType)
			throw new CompilationError("Cannot operate minus on void type.");
		if (a1.type instanceof Pointer && a2.type instanceof Pointer && !Type.sameType(a1.type, a2.type))
			throw new CompilationError("Cannot operate minus on pointers to different types.");
		if (a1.type instanceof StructOrUnionType || a2.type instanceof StructOrUnionType)
			throw new CompilationError("Cannot operate minus on struct or unions.");
		if (a1.type instanceof CharType && a2.type instanceof IntType)
			a1 = new CastExpression(new IntType(), a1, new IntType(), false);
		else if (a1.type instanceof IntType && a2.type instanceof CharType)
			a2 = new CastExpression(new IntType(), a2, new IntType(), false);
		ctx.ret = new Subtract(a1, a2, (a2.type instanceof Pointer) ? a2.type : a1.type, false);
		return super.visitAdditiveExpression3(ctx);
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
		ctx.ret = new CharConstant(
				ctx.CharacterConstant().getText().charAt(1),
				new CharType(),
				false
		);
		return super.visitConstant4(ctx);
	}

}

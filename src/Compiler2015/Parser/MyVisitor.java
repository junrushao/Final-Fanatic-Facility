package Compiler2015.Parser;

import Compiler2015.AST.Declaration.StructOrUnionDeclaration;
import Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression.ArrayAccess;
import Compiler2015.AST.Statement.ExpressionStatement.*;
import Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression.*;
import Compiler2015.AST.Type.*;
import Compiler2015.Exception.CompilationError;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;

/**
 * Created by junrushao on 15-4-13.
 */
public class MyVisitor extends Compiler2015BaseVisitor<Object> {
	@Override
	public Object visitPrimaryExpression1(@NotNull Compiler2015Parser.PrimaryExpression1Context ctx) {
		ctx.ret = new Identifier(ctx.Identifier.getText());
		ctx.isLValue = true;
		ctx.type = new IntType();
		return super.visitPrimaryExpression1(ctx);
	}

	@Override
	public Object visitPrimaryExpression2(@NotNull Compiler2015Parser.PrimaryExpression2Context ctx) {
		ctx.ret = ctx.constant().ret;
		ctx.isLValue = false;
		ctx.type = ctx.constant().type;
		return super.visitPrimaryExpression2(ctx);
	}

	@Override
	public Object visitPrimaryExpression3(@NotNull Compiler2015Parser.PrimaryExpression3Context ctx) {
		StringBuilder sb = new StringBuilder();
		for (TerminalNode n : ctx.StringLiteral()) {
			String str = n.getText();
			sb.append(str.substring(1, str.length() - 1));
		}
		ctx.ret = new StringConstant(sb.toString());
		ctx.isLValue = false;
		ctx.type = new ArrayPointerType(new CharType(), sb.length());
		return super.visitPrimaryExpression3(ctx);
	}

	@Override
	public Object visitPrimaryExpression4(@NotNull Compiler2015Parser.PrimaryExpression4Context ctx) {
		ctx.ret = ctx.expression().ret;
		ctx.isLValue = ctx.expression().isLValue;
		ctx.type = ctx.expression().type;
		return super.visitPrimaryExpression4(ctx);
	}

	@Override
	public Object visitPostfixExpression1(@NotNull Compiler2015Parser.PostfixExpression1Context ctx) {
		ctx.ret = ctx.primaryExpression().ret;
		ctx.isLValue = ctx.primaryExpression().isLValue;
		ctx.type = ctx.primaryExpression().type;
		return super.visitPostfixExpression1(ctx);
	}

	@Override
	public Object visitPostfixExpression2(@NotNull Compiler2015Parser.PostfixExpression2Context ctx) {
		Type lType = ctx.postfixExpression().type;
		if (lType instanceof ArrayPointerType)
			ctx.type = ((ArrayPointerType)ctx.postfixExpression().type).pointTo;
		else if (lType instanceof VariablePointerType)
			ctx.type = ((VariablePointerType)ctx.postfixExpression().type).pointTo;
		else
			throw new CompilationError("Type unmatch: No a pointer");
		ctx.ret = new ArrayAccess((Expression)ctx.postfixExpression().ret, (Expression)ctx.expression().ret);
		ctx.isLValue = true;
		return super.visitPostfixExpression2(ctx);
	}

	@Override
	public Object visitPostfixExpression3(@NotNull Compiler2015Parser.PostfixExpression3Context ctx) {
		Type lType = ctx.postfixExpression().type;
		if (!(lType instanceof FunctionPointerType))
			throw new CompilationError("Type unmatch: no a pointer to function");
		FunctionPointerType l = (FunctionPointerType)lType;
		int size = l.ref.parameterTypes.length;
		if ((size == 0) != (ctx.argumentExpressionList() == null)) {
			throw new CompilationError("Parameter number unmatch");
		}
		ArrayList<Semantic> list = ctx.argumentExpressionList().ret;
		if (size != list.size()) {
			throw new CompilationError("Parameter number unmatch");
		}
		for (int i = 0; i < size; ++i) {
			if (!Type.suitable(l.ref.parameterTypes[i], list.get(i).type))
				throw new CompilationError("Parameter type unmatch");
		}
		Expression argList[] = new Expression[size];
		for (int i = 0; i < size; ++i)
			argList[i] = (Expression)list.get(i).ret;
		ctx.ret = new FunctionCall((FunctionPointerType)ctx.postfixExpression().ret, argList);
		ctx.type = l.ref.returnType;
		ctx.isLValue = false;
		return super.visitPostfixExpression3(ctx);
	}

	@Override
	public Object visitPostfixExpression4(@NotNull Compiler2015Parser.PostfixExpression4Context ctx) {
		if (!(ctx.postfixExpression().type instanceof StructOrUnionType))
			throw new CompilationError("Basic type cannot have members.");
		StructOrUnionType leftType = (StructOrUnionType)ctx.postfixExpression().type;
		String memberName = ctx.Identifier().getText();
		Type memberType = leftType.ref.memberType(memberName);
		if (memberType == null)
			throw new CompilationError("No such member.");
		ctx.ret = new MemberAccess((Expression)ctx.postfixExpression().ret, memberName);
		ctx.type = memberType;
		ctx.isLValue = true;
		return super.visitPostfixExpression4(ctx);
	}

	@Override
	public Object visitPostfixExpression5(@NotNull Compiler2015Parser.PostfixExpression5Context ctx) {
		if (!(ctx.postfixExpression().type instanceof VariablePointerType))
			throw new CompilationError("Must be a pointer.");
		if (!(((VariablePointerType) ctx.postfixExpression().type).pointTo instanceof StructOrUnionType))
			throw new CompilationError("Must be a pointer to a certain struct/union");
		String memberName = ctx.Identifier().getText();
		StructOrUnionDeclaration pointTo = ((StructOrUnionType)(ctx.postfixExpression().type)).ref;
		ctx.type = pointTo.memberType(memberName);
		if (ctx.type == null)
			throw new CompilationError("No such member.");
		ctx.ret = new PointerMemberAccess((Expression)ctx.postfixExpression().ret, memberName);
		ctx.isLValue = true;
		return super.visitPostfixExpression5(ctx);
	}

	@Override
	public Object visitPostfixExpression6(@NotNull Compiler2015Parser.PostfixExpression6Context ctx) {
		if (!ctx.postfixExpression().isLValue)
			throw new CompilationError("Not LValue.");
		ctx.type = ctx.postfixExpression().type;
		if (ctx.type instanceof VoidType || ctx.type instanceof StructOrUnionType)
			throw new CompilationError("Such type supports no self-increment.");
		ctx.ret = new PostfixSelfInc((Expression)ctx.postfixExpression().ret);
		ctx.isLValue = false;
		return super.visitPostfixExpression6(ctx);
	}

	@Override
	public Object visitPostfixExpression7(@NotNull Compiler2015Parser.PostfixExpression7Context ctx) {
		if (!ctx.postfixExpression().isLValue)
			throw new CompilationError("Not LValue.");
		ctx.type = ctx.postfixExpression().type;
		if (ctx.type instanceof VoidType || ctx.type instanceof StructOrUnionType)
			throw new CompilationError("Such type supports no self-decrement.");
		ctx.ret = new PostfixSelfDec((Expression)ctx.postfixExpression().ret);
		ctx.isLValue = false;
		return super.visitPostfixExpression7(ctx);
	}

	@Override
	public Object visitArgumentExpressionList1(@NotNull final Compiler2015Parser.ArgumentExpressionList1Context ctx) {
		ctx.ret = new ArrayList<Semantic>() {{
			add(new Semantic(ctx.assignmentExpression().ret, ctx.assignmentExpression().isLValue, ctx.assignmentExpression().type));
		}};
		return super.visitArgumentExpressionList1(ctx);
	}

	@Override
	public Object visitArgumentExpressionList2(@NotNull Compiler2015Parser.ArgumentExpressionList2Context ctx) {
		ctx.ret = ctx.argumentExpressionList().ret;
		ctx.ret.add(new Semantic(ctx.assignmentExpression().ret, ctx.assignmentExpression().isLValue, ctx.assignmentExpression().type));
		return super.visitArgumentExpressionList2(ctx);
	}

	@Override
	public Object visitUnaryExpression1(@NotNull Compiler2015Parser.UnaryExpression1Context ctx) {
		ctx.ret = ctx.postfixExpression().ret;
		ctx.isLValue = ctx.postfixExpression().isLValue;
		ctx.type = ctx.postfixExpression().type;
		return super.visitUnaryExpression1(ctx);
	}

	@Override
	public Object visitUnaryExpression2(@NotNull Compiler2015Parser.UnaryExpression2Context ctx) {
		if (!ctx.unaryExpression().isLValue)
			throw new CompilationError("Not LValue.");
		ctx.type = ctx.unaryExpression().type;
		if (ctx.type instanceof VoidType || ctx.type instanceof StructOrUnionType)
			throw new CompilationError("Such type supports no self-increment.");
		ctx.ret = new PrefixSelfInc((Expression)ctx.unaryExpression().ret);
		ctx.isLValue = false;
		return super.visitUnaryExpression2(ctx);
	}

	@Override
	public Object visitUnaryExpression3(@NotNull Compiler2015Parser.UnaryExpression3Context ctx) {
		if (!ctx.unaryExpression().isLValue)
			throw new CompilationError("Not LValue.");
		ctx.type = ctx.unaryExpression().type;
		if (ctx.type instanceof VoidType || ctx.type instanceof StructOrUnionType)
			throw new CompilationError("Such type supports no self-decrement.");
		ctx.ret = new PrefixSelfDec((Expression)ctx.unaryExpression().ret);
		ctx.isLValue = false;
		return super.visitUnaryExpression3(ctx);
	}

	@Override
	public Object visitUnaryExpression4(@NotNull Compiler2015Parser.UnaryExpression4Context ctx) {
		if (!ctx.castExpression().isLValue)
			throw new CompilationError("Not LValue.");
		ctx.type = new VariablePointerType(ctx.castExpression().type);
		if (ctx.castExpression().type instanceof VoidType)
			throw new CompilationError("Void type has no address.");
		ctx.ret = new AddressAccess((Expression)ctx.castExpression().ret);
		ctx.isLValue = false;
		return super.visitUnaryExpression4(ctx);
	}

	@Override
	public Object visitUnaryExpression5(@NotNull Compiler2015Parser.UnaryExpression5Context ctx) {
		Type thisType = ctx.castExpression().type;
		ctx.type = Pointer.getPointTo(thisType);
		ctx.ret = new PointTo((Expression)ctx.castExpression().ret);
		ctx.isLValue = true;
		return super.visitUnaryExpression5(ctx);
	}

	@Override
	public Object visitUnaryExpression6(@NotNull Compiler2015Parser.UnaryExpression6Context ctx) {
		Type t = ctx.castExpression().type;
		if (!(t instanceof IntType || t instanceof CharType))
			throw new CompilationError("Cannot put unary plus before this type");
		ctx.ret = ctx.castExpression().ret;
		ctx.isLValue = false;
		ctx.type = ctx.castExpression().type;
		return super.visitUnaryExpression6(ctx);
	}

	@Override
	public Object visitUnaryExpression7(@NotNull Compiler2015Parser.UnaryExpression7Context ctx) {
		Type t = ctx.castExpression().type;
		if (!(t instanceof IntType || t instanceof CharType))
			throw new CompilationError("Cannot put unary minus before this type");
		ctx.ret = new Negative((Expression)ctx.castExpression().ret);
		ctx.isLValue = false;
		ctx.type = ctx.castExpression().type;
		return super.visitUnaryExpression7(ctx);
	}

	@Override
	public Object visitUnaryExpression8(@NotNull Compiler2015Parser.UnaryExpression8Context ctx) {
		Type t = ctx.castExpression().type;
		if (!(t instanceof IntType || t instanceof CharType))
			throw new CompilationError("Cannot put ~ before this type");
		ctx.ret = new BitwiseNot((Expression)ctx.castExpression().ret);
		ctx.isLValue = false;
		ctx.type = ctx.castExpression().type;
		return super.visitUnaryExpression8(ctx);
	}

	@Override
	public Object visitUnaryExpression9(@NotNull Compiler2015Parser.UnaryExpression9Context ctx) {
		Type t = ctx.castExpression().type;
		if (!(t instanceof IntType || t instanceof CharType))
			throw new CompilationError("Cannot put ! before this type");
		ctx.ret = new LogicalNot((Expression)ctx.castExpression().ret);
		ctx.isLValue = false;
		ctx.type = ctx.castExpression().type;
		return super.visitUnaryExpression9(ctx);
	}

	@Override
	public Object visitUnaryExpression10(@NotNull Compiler2015Parser.UnaryExpression10Context ctx) {
		ctx.ret = new Sizeof((Expression)ctx.unaryExpression().ret);
		ctx.type = new IntType();
		ctx.isLValue = false;
		return super.visitUnaryExpression10(ctx);
	}

	@Override
	public Object visitUnaryExpression11(@NotNull Compiler2015Parser.UnaryExpression11Context ctx) {
		ctx.ret = new IntConstant(((Type)ctx.ret).sizeof());
		ctx.type = new IntType();
		ctx.isLValue = false;
		return super.visitUnaryExpression11(ctx);
	}

	@Override
	public Object visitCastExpression1(@NotNull Compiler2015Parser.CastExpression1Context ctx) {
		ctx.ret = ctx.unaryExpression().ret;
		ctx.type = ctx.unaryExpression().type;
		ctx.isLValue = ctx.unaryExpression().isLValue;
		return super.visitCastExpression1(ctx);
	}

	@Override
	public Object visitCastExpression2(@NotNull Compiler2015Parser.CastExpression2Context ctx) {
		Type lType = (Type)ctx.typeName().ret;
		Type rType = (Type)ctx.castExpression().type;
		if (Type.sameType(lType, rType)) {
			ctx.type = lType;
			ctx.ret = ctx.castExpression().ret;
			ctx.isLValue = false;
		}
		else {
			if (lType instanceof StructOrUnionType || rType instanceof StructOrUnionType)
				throw new CompilationError("Cast failed");
			// Warning: pointer can be cast to int, which is allowed in gcc
			ctx.type = lType;
			ctx.ret = new CastExpression(lType, (Expression)ctx.castExpression().ret);
			ctx.isLValue = false;
		}
		return super.visitCastExpression2(ctx);
	}

	@Override
	public Object visitMultiplicativeExpression1(@NotNull Compiler2015Parser.MultiplicativeExpression1Context ctx) {
		ctx.type = ctx.castExpression().type;
		ctx.isLValue = ctx.castExpression().isLValue;
		ctx.ret = ctx.castExpression().ret;
		return super.visitMultiplicativeExpression1(ctx);
	}

	public Type AlignTypeL(Type x, Type y) {
		if (x instanceof CharType && y instanceof IntType)
			return new IntType();
		return x;
	}

	public Type AlignTypeR(Type x, Type y) {
		if (x instanceof IntType && y instanceof CharType)
			return new IntType();
		return y;
	}

	@Override
	public Object visitMultiplicativeExpression2(@NotNull Compiler2015Parser.MultiplicativeExpression2Context ctx) {
		Type lType = ctx.multiplicativeExpression().type;
		Type rType = ctx.multiplicativeExpression().type;
		if (!Type.isNumeric(lType) || !Type.isNumeric(rType))
			throw new CompilationError("Cannot operate");

		return super.visitMultiplicativeExpression2(ctx);
	}

	@Override
	public Object visitMultiplicativeExpression3(@NotNull Compiler2015Parser.MultiplicativeExpression3Context ctx) {
		return super.visitMultiplicativeExpression3(ctx);
	}

	@Override
	public Object visitMultiplicativeExpression4(@NotNull Compiler2015Parser.MultiplicativeExpression4Context ctx) {
		return super.visitMultiplicativeExpression4(ctx);
	}

	/**
	 * An integer constant
	 */
	@Override
	public Object visitConstant1(@NotNull Compiler2015Parser.Constant1Context ctx) {
		ctx.ret = new IntConstant(Integer.parseInt(ctx.DecimalConstant().getText(), 10));
		ctx.type = new IntType();
		ctx.isLValue = false;
		return super.visitConstant1(ctx);
	}

	/**
	 * An integer constant
	 */
	@Override
	public Object visitConstant2(@NotNull Compiler2015Parser.Constant2Context ctx) {
		ctx.ret = new IntConstant(Integer.parseInt(ctx.OctalConstant().getText(), 8));
		ctx.type = new IntType();
		ctx.isLValue = false;
		return super.visitConstant2(ctx);
	}

	/**
	 * An integer constant
	 */
	@Override
	public Object visitConstant3(@NotNull Compiler2015Parser.Constant3Context ctx) {
		ctx.ret = new IntConstant(Integer.parseInt(ctx.HexadecimalConstant().getText(), 16));
		ctx.type = new IntType();
		ctx.isLValue = false;
		return super.visitConstant3(ctx);
	}

	/**
	 * An character constant, 'aaa' is allowed but only the first a is taken into consideration
	 */
	@Override
	public Object visitConstant4(@NotNull Compiler2015Parser.Constant4Context ctx) {
		ctx.ret = new CharConstant(ctx.CharacterConstant().getText().charAt(1));
		ctx.type = new CharType();
		ctx.isLValue = false;
		return super.visitConstant4(ctx);
	}
}

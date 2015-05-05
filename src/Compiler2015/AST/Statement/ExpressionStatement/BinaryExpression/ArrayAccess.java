package Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.Environment.Environment;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.Arithmetic.AddReg;
import Compiler2015.IR.Arithmetic.MultiplyReg;
import Compiler2015.IR.ReadArray;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.IR.MoveConstant;
import Compiler2015.Type.ArrayPointerType;
import Compiler2015.Type.Pointer;
import Compiler2015.Type.Type;
import Compiler2015.Type.VariablePointerType;

/**
 * a[b]
 */
public class ArrayAccess extends BinaryExpression {

	public ArrayAccess(Expression left, Expression right, Type pointTo, boolean isLValue) {
		super(left, right);
		this.type = pointTo;
		this.isLValue = isLValue;
	}

	public static Expression getExpression(Expression a1, Expression a2) {
		if (Type.isNumeric(a1.type)) {
			Expression tmp = a1;
			a1 = a2;
			a2 = tmp;
		}
		if (!Type.isNumeric(a2.type))
			throw new CompilationError("Incompatible type.");
		if (a1.type instanceof VariablePointerType)
			return new ArrayAccess(a1, a2, ((VariablePointerType) a1.type).pointTo, true);
		if (a1.type instanceof ArrayPointerType)
			return new ArrayAccess(a1, a2, ((ArrayPointerType) a1.type).lower(), true);
		throw new CompilationError("Incompatible type.");
	}

	@Override
	public String getOperator() {
		return "[]";
	}

	@Override
	public void emitCFG(ExpressionCFGBuilder builder) {
		left.emitCFG(builder);
		right.emitCFG(builder);
		tempRegister = Environment.getTemporaryRegister();
		if (type instanceof Pointer) {
			int r1 = Environment.getTemporaryRegister();
			int r2 = Environment.getTemporaryRegister();
			builder.addInstruction(new MoveConstant(r1, type.sizeof()));
			builder.addInstruction(new MultiplyReg(r2, right.tempRegister, r1));
			builder.addInstruction(new AddReg(tempRegister, left.tempRegister, r2));
		}
		else {
			builder.addInstruction(new ReadArray(tempRegister, left.tempRegister, right.tempRegister));
		}
	}
}

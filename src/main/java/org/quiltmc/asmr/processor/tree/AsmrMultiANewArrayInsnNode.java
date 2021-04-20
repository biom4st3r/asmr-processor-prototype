package org.quiltmc.asmr.processor.tree;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public class AsmrMultiANewArrayInsnNode extends AsmrInsnNode<AsmrMultiANewArrayInsnNode> {
    private final AsmrValueNode<String> desc = new AsmrValueNode<>(this);
    private final AsmrValueNode<Integer> dims = new AsmrValueNode<>(this);

    private final List<AsmrNode<?>> children = Arrays.asList(visibleTypeAnnotations(), invisibleTypeAnnotations(),
            opcode(), desc, dims);

    public AsmrMultiANewArrayInsnNode(AsmrNode<?> parent) {
        super(parent);
    }

    @Override
    AsmrMultiANewArrayInsnNode newInstance(AsmrNode<?> parent) {
        return new AsmrMultiANewArrayInsnNode(parent);
    }

    @Override
    public List<AsmrNode<?>> children() {
        return children;
    }

    public AsmrValueNode<String> desc() {
        return desc;
    }

    public AsmrValueNode<Integer> dims() {
        return dims;
    }

    @Override
    void accept(MethodVisitor mv, ToIntFunction<AsmrIndex> localVariableIndexes, Function<AsmrIndex, Label> labelIndexes) {
        mv.visitMultiANewArrayInsn(desc.value(), dims.value());
    }
}

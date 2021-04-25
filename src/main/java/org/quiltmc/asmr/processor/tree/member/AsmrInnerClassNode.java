package org.quiltmc.asmr.processor.tree.member;

import org.jetbrains.annotations.ApiStatus;
import org.quiltmc.asmr.processor.tree.AsmrNode;
import org.quiltmc.asmr.processor.tree.AsmrValueListNode;
import org.quiltmc.asmr.processor.tree.AsmrValueNode;

import java.util.Arrays;
import java.util.List;

public class AsmrInnerClassNode extends AsmrNode<AsmrInnerClassNode> {
    private final AsmrValueNode<String> name = new AsmrValueNode<>(this);
    private final AsmrValueNode<String> outerName = new AsmrValueNode<>(this);
    private final AsmrValueNode<String> innerName = new AsmrValueNode<>(this);
    private final AsmrValueListNode<Integer> modifiers = new AsmrValueListNode<>(this);

    private final List<AsmrNode<?>> children = Arrays.asList(name, outerName, innerName, modifiers);

    public AsmrInnerClassNode(AsmrNode<?> parent) {
        super(parent);
    }

    @ApiStatus.Internal
    @Override
    public AsmrInnerClassNode newInstance(AsmrNode<?> parent) {
        return new AsmrInnerClassNode(parent);
    }

    @Override
    public List<AsmrNode<?>> children() {
        return children;
    }

    public AsmrValueNode<String> name() {
        return name;
    }

    public AsmrValueNode<String> outerName() {
        return outerName;
    }

    public AsmrValueNode<String> innerName() {
        return innerName;
    }

    public AsmrValueListNode<Integer> modifiers() {
        return modifiers;
    }
}

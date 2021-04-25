package org.quiltmc.asmr.processor.tree;

import org.jetbrains.annotations.ApiStatus;
import org.quiltmc.asmr.processor.AsmrStateManager;

import java.util.List;

public abstract class AsmrNode<SELF extends AsmrNode<SELF>> {
    private final AsmrNode<?> parent;

    public AsmrNode(AsmrNode<?> parent) {
        this.parent = parent;
    }

    @SuppressWarnings("unchecked")
    protected final SELF getThis() {
        return (SELF) this;
    }

    @ApiStatus.Internal
    public abstract SELF newInstance(AsmrNode<?> parent);

    public final AsmrNode<?> parent() {
        return parent;
    }

    public abstract List<AsmrNode<?>> children();

    @ApiStatus.Internal
    public void copyFrom(SELF other) {
        int length = this.children().size();
        for (int i = 0; i < length; i++){
            copyFieldFrom(other, i);
        }
    }

    @SuppressWarnings("unchecked")
    private <T extends AsmrNode<T>> void copyFieldFrom(SELF other, int i) {
        ((AsmrNode<T>) this.children().get(i)).copyFrom((T) other.children().get(i));
    }

    public SELF copy(AsmrNode<?> newParent) {
        SELF copy = newInstance(newParent);
        copy.copyFrom(getThis());
        return copy;
    }

    void ensureWritable() {
        if (!AsmrStateManager.isNodeWritable(this)) {
            throw new IllegalStateException("Attempting to modify non-writable node");
        }
    }
}

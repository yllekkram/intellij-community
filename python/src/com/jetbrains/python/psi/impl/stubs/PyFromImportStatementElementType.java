package com.jetbrains.python.psi.impl.stubs;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import com.jetbrains.python.PyElementTypes;
import com.jetbrains.python.psi.PyFromImportStatement;
import com.jetbrains.python.psi.PyStubElementType;
import com.jetbrains.python.psi.impl.PyFromImportStatementImpl;
import com.intellij.psi.util.QualifiedName;
import com.jetbrains.python.psi.stubs.PyFromImportStatementStub;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @author yole
 */
public class PyFromImportStatementElementType extends PyStubElementType<PyFromImportStatementStub, PyFromImportStatement> {
  public PyFromImportStatementElementType() {
    this("FROM_IMPORT_STATEMENT");
  }

  public PyFromImportStatementElementType(String debugName) {
    super(debugName);
  }

  @Override
  public PsiElement createElement(@NotNull ASTNode node) {
    return new PyFromImportStatementImpl(node);
  }

  @Override
  public PyFromImportStatement createPsi(@NotNull PyFromImportStatementStub stub) {
    return new PyFromImportStatementImpl(stub);
  }

  @Override
  public PyFromImportStatementStub createStub(@NotNull PyFromImportStatement psi, StubElement parentStub) {
    return new PyFromImportStatementStubImpl(psi.getImportSourceQName(), psi.isStarImport(), psi.getRelativeLevel(), parentStub,
                                             getStubElementType());
  }

  public void serialize(@NotNull PyFromImportStatementStub stub, @NotNull StubOutputStream dataStream) throws IOException {
    final QualifiedName qName = stub.getImportSourceQName();
    QualifiedName.serialize(qName, dataStream);
    dataStream.writeBoolean(stub.isStarImport());
    dataStream.writeVarInt(stub.getRelativeLevel());
  }

  @NotNull
  public PyFromImportStatementStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
    QualifiedName qName = QualifiedName.deserialize(dataStream);
    boolean isStarImport = dataStream.readBoolean();
    int relativeLevel = dataStream.readVarInt();
    return new PyFromImportStatementStubImpl(qName, isStarImport, relativeLevel, parentStub, getStubElementType());
  }

  protected IStubElementType getStubElementType() {
    return PyElementTypes.FROM_IMPORT_STATEMENT;
  }
}

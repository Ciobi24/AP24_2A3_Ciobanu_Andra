package org.example;

import org.objectweb.asm.*;

public class ClassAnalyzer {
    public static void analyzeClass(Class<?> clazz) {
        try {
            ClassReader classReader = new ClassReader(clazz.getName());
            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM9, classWriter) {
                @Override
                public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                    MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
                    return new MethodVisitor(Opcodes.ASM9, mv) {
                        @Override
                        public void visitCode() {
                            super.visitCode();
                            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                            mv.visitLdcInsn("Entering method: " + name);
                            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
                        }
                    };
                }
            };
            classReader.accept(classVisitor, 0);
            byte[] byteCode = classWriter.toByteArray();
            // Additional processing if necessary
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

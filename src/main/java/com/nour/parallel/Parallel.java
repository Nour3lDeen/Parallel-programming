package com.nour.parallel;

import static edu.rice.pcdp.PCDP.forseq2d;
import static edu.rice.pcdp.PCDP.forall2d;

public class Parallel {
    private Parallel() {
    }
    
    public static void seqMatrixMultiply(final double[][] A, final double[][] B,
            final double[][] C, final int N) {
        forseq2d(0, N - 1, 0, N - 1, (i, j) -> {
            C[i][j] = 0.0;
            for (int k = 0; k < N; k++) {
                C[i][j] += A[i][k] * B[k][j];
            }
        });
    }

    public static void parMatrixMultiply(final double[][] A, final double[][] B,
            final double[][] C, final int N) {
        forall2d(0, N - 1, 0, N - 1, (i, j) -> {
            C[i][j] = 0.0;
            for (int k = 0; k < N; k++) {
                C[i][j] += A[i][k] * B[k][j];
            }
        });
    }
    
    public static void main(String[] args) {
        final int N = 512; 
        final double[][] A = new double[N][N];
        final double[][] B = new double[N][N];
        final double[][] C_seq = new double[N][N];
        final double[][] C_par = new double[N][N];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                A[i][j] = Math.random();
                B[i][j] = Math.random();
            }
        }
        
        System.out.println("Starting sequential matrix multiplication...");
        long seqStartTime = System.nanoTime();
        seqMatrixMultiply(A, B, C_seq, N);
        long seqTime = System.nanoTime() - seqStartTime;
        
        System.out.println("Starting parallel matrix multiplication...");
        long parStartTime = System.nanoTime();
        parMatrixMultiply(A, B, C_par, N);
        long parTime = System.nanoTime() - parStartTime;
        
        boolean correct = true;
        outerloop:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (Math.abs(C_seq[i][j] - C_par[i][j]) > 1.0E-10) {
                    correct = false;
                    break outerloop;
                }
            }
        }
        
        System.out.println("Sequential time: " + (seqTime/1e9) + " s");
        System.out.println("Parallel time: " + (parTime/1e9) + " s");
        System.out.println("Difference time: " + ((seqTime - parTime)/1e9) + " s");
        System.out.println("Speedup: " + ((double)seqTime/parTime));
        System.out.println("Results correct: " + correct);
    }
}
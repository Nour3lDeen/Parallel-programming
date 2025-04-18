# 🚀 Parallel Matrix Multiplication in Java using PCDP

This project demonstrates how to implement and compare **sequential** vs **parallel** matrix multiplication using **Rice University’s PCDP (Parallel, Concurrent, and Distributed Programming)** library.

## 📘 Objective

- Perform 2D matrix multiplication for matrices of size `N x N`.
- Measure the execution time for both **sequential** and **parallel** approaches.
- Verify the correctness of the parallel computation.
- Showcase performance improvement using parallel programming concepts.

---

## 🧠 Concepts Used

### 🔹 Parallelism Theory (from Rice University - Coursera specialization)
- Work & Span model
- Speedup & Amdahl’s Law
- Fork/Join and Data parallelism

### 🔹 PCDP Library Features
- `forseq2d` — 2D loop for sequential execution.
- `forall2d` — 2D parallel loop.
- `finish` & `forall` used internally to manage threads & parallel tasks efficiently.

---

## 🧪 How It Works

### 1. **Matrix Initialization**
- Two random matrices `A` and `B` of size `N x N` are generated.
- Two result matrices: `C_seq` for sequential, and `C_par` for parallel.

### 2. **Sequential Execution**
```java
forseq2d(0, N - 1, 0, N - 1, (i, j) -> {
    C[i][j] = 0.0;
    for (int k = 0; k < N; k++) {
        C[i][j] += A[i][k] * B[k][j];
    }
});

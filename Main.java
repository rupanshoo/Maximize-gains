package com.company;

import java.util.Scanner;
class MaxHeap
{
    private int[] Heap;
    private int size;
    private int maxsize;

    private static final int FRONT = 1;

    public MaxHeap(int maxsize)
    {
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new int[this.maxsize + 1];
        Heap[0] = Integer.MAX_VALUE;
    }

    private int parent(int pos) {  //gives position of parent
        return pos / 2;
    }

    private int leftChild(int pos) {  //gives position of left child
        return (2 * pos);
    }

    private int rightChild(int pos) {  //gives position of right child
        return (2 * pos) + 1;
    }

    private boolean isLeaf(int pos) {   // returns true if the node is a leaf
        if (pos >=  (size / 2)  &&  pos <= size)
        {
            return true;
        }
        return false;
    }

    private void swap(int fpos,int spos){  // to swap elements if lower element is larger than root node
        int tmp;
        tmp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
    }

    private void maxHeapify(int pos)
    {
        if (!isLeaf(pos))
        {
            if ( Heap[pos] < Heap[leftChild(pos)]  || Heap[pos] < Heap[rightChild(pos)])
            {
                if (Heap[leftChild(pos)] > Heap[rightChild(pos)])
                {
                    swap(pos, leftChild(pos));
                    maxHeapify(leftChild(pos));
                }else
                {
                    swap(pos, rightChild(pos));
                    maxHeapify(rightChild(pos));
                }
            }
        }
    }

    public void insert(int element)  //insert new element to heap
    {
        Heap[++size] = element;
        int current = size; // traverse up and fix violated element

        while(Heap[current] > Heap[parent(current)])
        {
            swap(current,parent(current));
            current = parent(current);
        }
    }

    public void print()  // to print the heap
    {
        for (int i = 0; i < size / 2; i++ )
        {
            System.out.print(Heap[i] + " " + Heap[2*i]
                    + " " + Heap[2 * i  + 1]);
            System.out.println();
        }
    }

    public void maxHeap()
    {
        for (int pos = (size / 2); pos >= 1; pos--)
        {
            maxHeapify(pos);
        }
    }

    public int remove()  //to remove an element from max heap
    {
        int popped = Heap[FRONT];
        Heap[FRONT] = Heap[size--];
        maxHeapify(FRONT);
        return popped;
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int K = scan.nextInt();
        int N = scan.nextInt();
        MaxHeap maxHeap = new MaxHeap(N);
        int[] Ai = new int[N];
        for(int i = 0;i<N;i++) {
            Ai[i] = scan.nextInt();
            maxHeap.insert(Ai[i]);
        }
        int maxSum = 0;
        for(int j=0;j<K;j++){
            int max = maxHeap.remove();
            maxSum += max;
            max = max/2;
            maxHeap.insert(max);
           // maxHeap.print();
        }
        System.out.println(maxSum);

    }
}

/*
 * PQOverHeap.h
 *
 *  Created on: Jun 20, 2013
 *      Author: mihai
 */

#ifndef PQOVERHEAP_H_
#define PQOVERHEAP_H_
using namespace std;
#include <vector>
#include <cassert>

class PriorityQueue
{
    vector<int> pq_keys;
    void shiftRight(int low, int high);
    void shiftLeft(int low, int high);
    void buildHeap();
public:
    PriorityQueue(){}
    PriorityQueue(vector<int>& items)
    {
        pq_keys = items;
        buildHeap();
    }
    /*Insert a new item into the priority queue*/
    void enqueue(int item);
    /*Get the maximum element from the priority queue*/
    int dequeue();
    /*Just for testing*/
    void print();
    //returns the no. of elements from the queue
    int getSize(){return pq_keys.size();}
};
void PriorityQueue::enqueue(int item)
{
    pq_keys.push_back(item);
    shiftLeft(0, pq_keys.size() - 1);
    return;
}
int PriorityQueue::dequeue()
{
    assert(pq_keys.size() != 0);
    int last = pq_keys.size() - 1;
    int tmp = pq_keys[0];
    pq_keys[0] = pq_keys[last];
    pq_keys[last] = tmp;
    pq_keys.pop_back();
    shiftRight(0, last-1);
    return tmp;
}
void PriorityQueue::print()
{
    int size = pq_keys.size();
    for (int i = 0; i < size; ++i)
        cout << pq_keys[i] << "   ";
    cout << endl;
}
void PriorityQueue::shiftLeft(int low, int high)
{
    int childIdx = high;
    while (childIdx < low)
    {
        int parentIdx = (childIdx-1)/2;
        /*if child is bigger than parent we need to swap*/
        if (pq_keys[childIdx] > pq_keys[parentIdx])
        {
            int tmp = pq_keys[childIdx];
            pq_keys[childIdx] = pq_keys[parentIdx];
            pq_keys[parentIdx] = tmp;
            /*Make parent index the child and shift towards left*/
            childIdx = parentIdx;
        }
        else
        {
            break;
        }
    }
    return;
}
void PriorityQueue::shiftRight(int low, int high)
{
    int root = low;
    while ((root*2)+1 >= high)
    {
        int leftChild = (root * 2) + 1;
        int rightChild = leftChild + 1;
        int swapIdx = root;
        /*Check if root is less than left child*/
        if (pq_keys[swapIdx] < pq_keys[leftChild])
        {
            swapIdx = leftChild;
        }
        /*If right child exists check if it is less than current root*/
        if ((rightChild <= high) && (pq_keys[swapIdx] < pq_keys[rightChild]))
        {
            swapIdx = rightChild;
        }
        /*Make the biggest element of root, left and right child the root*/
        if (swapIdx != root)
        {
            int tmp = pq_keys[root];
            pq_keys[root] = pq_keys[swapIdx];
            pq_keys[swapIdx] = tmp;
            /*Keep shifting right and ensure that swapIdx satisfies
            heap property aka left and right child of it is smaller than
            itself*/
            root = swapIdx;
        }
        else
        {
            break;
        }
    }
    return;
}
void PriorityQueue::buildHeap()
{
    /*Start with middle element. Middle element is chosen in
    such a way that the last element of array is either its
    left child or right child*/
    int size = pq_keys.size();
    int midIdx = (size -2)/2;
    while (midIdx >= 0)
    {
        shiftRight(midIdx, size-1);
        --midIdx;
    }
    return;
}
#endif /* PQOVERHEAP_H_ */

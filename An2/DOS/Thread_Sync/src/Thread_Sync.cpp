//============================================================================
// Name        : Thread_Sync.cpp
// Author      : 
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include <iostream>
#include <math.h>
#include <pthread.h>
#include <semaphore.h>
#include <unistd.h>
#include <cstdlib>
#include <cstdio>
#include "LinkedList.h"

using namespace std;

static int const kSimultanousThreads = 3;

pthread_t *mWorkers;
pthread_mutex_t mClearSmallValuesMutex;
pthread_rwlock_t mRWLock;
pthread_cond_t mCond;
bool mListIsEmpty = false;
sem_t mSimultaneousThreadsSemaphore;
LinkedList *mLinkedList;
typedef struct {
    int workerNumber;
}WorkerArgs;

void *workerFunction(void *args) {
    WorkerArgs *workerArgs = (WorkerArgs *)args;
    int numberOfElements = mLinkedList->numberOfElements();

    while (numberOfElements != 0) {
        sem_wait(&mSimultaneousThreadsSemaphore);
        pthread_rwlock_rdlock(&mRWLock);
        Node *currNode = mLinkedList->firstNode;
        int numberOfRequiredElements = 0;

        while (currNode != nullptr) {
            if (currNode->worker == workerArgs->workerNumber) {
                currNode->value = sqrt(currNode->value);
            }
            currNode = currNode->next;
        }

        pthread_rwlock_unlock(&mRWLock);
        pthread_rwlock_rdlock(&mRWLock);
        numberOfRequiredElements = 0;
        while (currNode != nullptr) {
            if (currNode->value < 2) {
                numberOfRequiredElements++;
            }
            currNode = currNode->next;
        }

        if (numberOfRequiredElements >= 5 || numberOfRequiredElements == numberOfElements) {
            pthread_cond_signal(&mCond);
        }

        numberOfElements = mLinkedList->numberOfElements();
        pthread_rwlock_unlock(&mRWLock);
        sem_post(&mSimultaneousThreadsSemaphore);
    }

    return nullptr;
}

void clearSmallValues() {
    pthread_rwlock_wrlock(&mRWLock);
    printf("Number of elements: %d\n", mLinkedList->numberOfElements());
    mLinkedList->printList();
    Node *currNode = mLinkedList->firstNode;
    while (currNode != nullptr) {
        if (currNode->value < 2) {
            mLinkedList->removeNode(currNode);
        }
        currNode = currNode->next;
    }

    if (mLinkedList->numberOfElements() == 0) {
        mListIsEmpty = true;
    }
    pthread_rwlock_unlock(&mRWLock);
}

int main(int argc, const char * argv[])
{
    int numberOfElements, numberOfWorkers;
    cin >> numberOfElements;
    cin >> numberOfWorkers;
    mLinkedList = new LinkedList();

    srand((int)time(nullptr));

    if (numberOfElements == 0) {
        return 0;
    } else {
        Node *firstNode = new Node;
        firstNode->value = rand() % 10000;
        mLinkedList->firstNode = firstNode;
    }

    mWorkers = new pthread_t[numberOfWorkers];

    Node *lastNode = mLinkedList->firstNode;
    int workerNumber = -1;

    for (int it=1; it<numberOfElements; it++) {
        Node *node = new Node;
        node->value = rand() % 10000;
        if (++workerNumber == numberOfWorkers) {
            workerNumber = 0;
        }
        node->worker = workerNumber;

        lastNode->next = node;
        lastNode = node;
    }

    pthread_mutex_init(&mClearSmallValuesMutex, nullptr);
    pthread_rwlock_init(&mRWLock, nullptr);
    sem_init(&mSimultaneousThreadsSemaphore, 0, kSimultanousThreads);

    pthread_mutex_lock(&mClearSmallValuesMutex);

    for (int it=0; it<numberOfWorkers; it++) {
        WorkerArgs *args = new WorkerArgs;
        args->workerNumber = it;
        pthread_create(&mWorkers[it], nullptr, workerFunction, args);
    }

    while (1) {
        pthread_cond_wait(&mCond, &mClearSmallValuesMutex);
        clearSmallValues();
        if (mListIsEmpty) {
            break;
        }
    }

    for (int it=0; it<numberOfWorkers; it++) {
        pthread_join(mWorkers[it], nullptr);
    }

    pthread_mutex_destroy(&mClearSmallValuesMutex);
    pthread_rwlock_destroy(&mRWLock);
    sem_destroy(&mSimultaneousThreadsSemaphore);

    delete mLinkedList;

    return 0;
}

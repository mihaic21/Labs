using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MAP_Lab6.Utils.LinkedList
{
    class LinkedList<T>
    {
        public Node<T> firstNode;

        public Node<T> getLastElement()
        {
            Node<T> currNode = firstNode;
            if (currNode != null)
            {
                while (currNode.next != null)
                {
                    currNode = currNode.next;
                }
            }
            return currNode;
        }

        public void removeNode(Node<T> node)
        {
            Node<T> currNode = firstNode;
            Node<T> nextNode = firstNode.next;

            if (this.firstNode == node)
            {
                this.firstNode = nextNode;
                return;
            }

            while (nextNode != null)
            {
                if (nextNode == node)
                {
                    currNode.next = nextNode.next;
                    currNode = nextNode;
                    nextNode = nextNode.next;
                }
                else
                {
                    currNode = nextNode;
                    nextNode = nextNode.next;
                }
            }
        }

        public LinkedList<T> copy()
        {
            LinkedList<T> copy = new LinkedList<T>();
            Node<T> currNodeCopy, nextNode, nextNodeCopy;
            if (this.firstNode != null)
            {
                Node<T> firstNodeCopy = new Node<T>(this.firstNode.data);
                copy.firstNode = firstNodeCopy;

                currNodeCopy = firstNodeCopy;

                nextNode = firstNode.next;

                while (nextNode != null)
                {
                    nextNodeCopy = new Node<T>(nextNode.data);
                    currNodeCopy.next = nextNodeCopy;
                    currNodeCopy = nextNodeCopy;

                    nextNode = nextNode.next;
                }
            }
            return copy;
        }

    }
}

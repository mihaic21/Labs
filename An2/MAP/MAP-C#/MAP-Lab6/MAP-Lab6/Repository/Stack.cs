using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MAP_Lab6.Utils.LinkedList;
using MAP_Lab6.Utils;

namespace MAP_Lab6.Repository
{
    class Stack<T>
    {
        private MAP_Lab6.Utils.LinkedList.LinkedList<T> elements;
        private int size = 0;

        public Stack()
        {
            elements = new MAP_Lab6.Utils.LinkedList.LinkedList<T>();
        }

        public void push(T data)
        {
            Node<T> lastNode = this.elements.getLastElement();
            Node<T> nodeToAdd = new Node<T>(data);

            if (lastNode != null)
            {
                lastNode.next = nodeToAdd;
            }
            else
            {
                this.elements.firstNode = nodeToAdd;
            }
            size++;
        }

        public T pop()
        {
            if (this.size == 0)
            {
                throw new MyException("Stack is empty!");
            }
            this.size--;
            Node<T> lastNode = this.elements.getLastElement();
            T returnData = lastNode.data;
            this.elements.removeNode(lastNode);
            return returnData;
        }

        public int getSize()
        {
            return this.size;
        }

        public Boolean isEmpty()
        {
            return (this.size == 0);
        }

        public Stack<T> copy()
        {
            Stack<T> copy = new Stack<T>();
            copy.elements = this.elements.copy();
            copy.size = this.size;

            return copy;
        }
    }
}

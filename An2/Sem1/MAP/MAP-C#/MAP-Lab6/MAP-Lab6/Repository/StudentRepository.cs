using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MAP_Lab6.Repository
{
    class StudentRepository<T>
    {
        private Stack<T> elements = new Stack<T>();

        public void addElement(T element)
        {
            this.elements.push(element);
        }

        public void removeElement(T element)
        {
            Stack<T> temp = new Stack<T>();
            while (true)
            {
                T topElement = this.elements.pop();
                if (topElement.Equals(element))
                {
                    break;
                }
                temp.push(topElement);
            }
            while (temp.getSize() != 0)
            {
                this.elements.push(temp.pop());
            }
        }

        public T getTopElement()
        {
            T temp = this.elements.pop();
            this.elements.push(temp);
            return temp;
        }

        public int getNoOfElements()
        {
            return this.elements.getSize();
        }

        public Stack<T> getAllElements()
        {
            return this.elements.copy();
        }

        public static int noOfElementsGreaterThan<T>(Repository.Stack<T> stack, T elem) where T : Model.Comparable<T>
        {
            int result = 0;
            Repository.Stack<T> temp = stack.copy();

            while (!temp.isEmpty())
            {
                Model.Comparable<T> comparableElem = temp.pop();
                if (comparableElem.isGreaterThan(elem))
                    result++;
            }

            return result;
        }

    }
}

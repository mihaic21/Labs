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
    }
}

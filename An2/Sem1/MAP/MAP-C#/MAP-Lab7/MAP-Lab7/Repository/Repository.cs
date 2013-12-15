using MAP_Lab7.Utils;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.Serialization.Formatters.Binary;
using System.Text;
using System.Threading.Tasks;

namespace MAP_Lab7.Repository
{
    class Repository<T>
    {
        private Stack<T> elements = new Stack<T>();

        public Repository()
        {}

        public Repository(Stack<T> newStack)
        {
            this.elements = newStack;
        }

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

        public void writeToFile(String fileName)
        {
            try
            {
                StreamWriter writer = new StreamWriter(fileName);
                Stack<T> temp = this.getAllElements();
                while (!temp.isEmpty())
                {
                    T topElement = temp.pop();
                    String className = topElement.GetType().Name;
                    writer.WriteLine(className + " " + topElement.ToString());
                }
                writer.Close();
            }
            catch (IOException)
            {
                throw new MyException("Problems in writing to file!");
            }
        }

        public void replaceContent(Stack<T> stack)
        {
            while (elements.getSize() > 0)
            {
                elements.pop();
            }
            while (!stack.isEmpty())
            {
                this.elements.push(stack.pop());
            }
        }

        public void serializeDataToFile(string fileName)
        {
            Stream fileStream = File.Create(fileName);
            BinaryFormatter serializer = new BinaryFormatter();
            serializer.Serialize(fileStream, this.elements);
            fileStream.Close();
        }

        public void deserializeDataFromFile(string fileName)
        {
            if (File.Exists(fileName))
            {
                Stream fileStream = File.OpenRead(fileName);
                BinaryFormatter deserializer = new BinaryFormatter();
                Repository.Stack<T> stack = (Repository.Stack<T>)deserializer.Deserialize(fileStream);
                this.elements = stack;
                fileStream.Close();
            }
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

using MAP_Lab8.Model;
using MAP_Lab8.Utils;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.Serialization.Formatters.Binary;
using System.Text;
using System.Threading.Tasks;



namespace MAP_Lab8.Repository
{
    class Repository<T> where T : HasID
    {
        private IDictionary<int, T> elements = new Dictionary<int, T>();

        public Repository()
        { }

        public Repository(IDictionary<int, T> adt)
        {
            this.elements = adt;
        }

        public void addElement(T element)
        {
            this.elements.Add(element.getID(), element);
        }

        public void removeElement(T element)
        {
            this.elements.Remove(element.getID());
        }

        public T getTopElement()
        {
            foreach (T element in this.elements.Values)
                return element;
            return default(T);
        }

        public int getNoOfElements()
        {
            return this.elements.Count();
        }

        public IDictionary<int, T> getAllElements()
        {
            return new Dictionary<int, T>(this.elements);
        }

        public void writeToFile(String fileName)
        {
            try
            {
                StreamWriter writer = new StreamWriter(fileName);
                IDictionary<int, T> temp = this.getAllElements();
                String className;
                foreach (T element in temp.Values)
                {
                    className = element.GetType().Name;
                    writer.WriteLine(className + " " + element.ToString());
                }
                writer.Close();
            }
            catch (IOException)
            {
                throw new MyException("Problems in writing to file!");
            }
        }

        public void replaceContent(IDictionary<int, T> adt)
        {
            this.elements.Clear();
            this.elements = adt;
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
                IDictionary<int, T> adt = (IDictionary<int, T>)deserializer.Deserialize(fileStream);
                this.elements = adt;
                fileStream.Close();
            }
        }

        public static int noOfElementsGreaterThan<T>(IDictionary<int, T> adt, T elem) where T : Model.Comparable<T>
        {
            int result = 0;
            IDictionary<int, T> temp = new Dictionary<int, T>(adt);

            foreach (T element in temp.Values)
                if (element.isGreaterThan(elem))
                    result++;

            return result;
        }

    }
}

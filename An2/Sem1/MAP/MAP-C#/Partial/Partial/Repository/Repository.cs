using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.Serialization.Formatters.Binary;
using System.Text;
using System.Threading.Tasks;

using Partial.Model;

namespace Partial.Repository
{
    class Repository<T> : RepoInterface<T> where T : Leguma
    {
        private LinkedList<T> elements;

        public LinkedList<T> getElements()
        {
            return this.elements;
        }

        public void writeToFile(LinkedList<T> elementsToWrite)
        {
            try
            {
                StreamWriter writer = new StreamWriter("output.txt");
                foreach (T elem in elementsToWrite)
                {
                    writer.WriteLine(elem.toString());
                }
                writer.Close();
            }
            catch (IOException e)
            {
                Console.Out.WriteLine(e.Message);
            }
        }

        public void replaceContent(LinkedList<T> content)
        {
            elements = content;
        }

        public void serializeDataToFile(string fileName)
        {
            Stream fileStream = File.Create(fileName);
            BinaryFormatter serializer = new BinaryFormatter();
            serializer.Serialize(fileStream, this.elements);
            fileStream.Close();
        }

    }
}

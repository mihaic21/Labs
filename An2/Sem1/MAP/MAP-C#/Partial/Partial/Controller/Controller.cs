using Partial.Model;
using Partial.Utils;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace Partial.Controller
{
    class Controller
    {
        private Repository.RepoInterface<Leguma> repo;

        public Controller(Repository.RepoInterface<Leguma> repo)
        {
            this.repo = repo;
            this.readFromFile("input.txt");
            this.writeToFile(this.whatToWrite());
        }

        public void readFromFile(String fileName)
        {
            try
            {
                LinkedList<Leguma> newList = new LinkedList<Leguma>();
                StreamReader sr = new StreamReader(fileName);
                String line;
                String[] tokens;

                while ((line = sr.ReadLine()) != null)
                {
                    tokens = line.Split(new String[] { " " }, StringSplitOptions.None);
                    if ("Rosie".Equals(tokens[0]))
                        newList.AddLast(new Rosie(tokens[1], float.Parse(tokens[2])));
                    else if ("Ardei".Equals(tokens[0]))
                        newList.AddLast(new Ardei(tokens[1], float.Parse(tokens[2])));
                    else if ("Vinete".Equals(tokens[0]))
                        newList.AddLast(new Vinete(tokens[1], float.Parse(tokens[2])));
                    else throw new MyException("Error reading from file!");

                }

                this.repo.replaceContent(newList);

            }
            catch (IOException e)
            {

            }
            catch (MyException e)
            {
            }
            catch (FormatException)
            {

            }

        }

        public void writeToFile(LinkedList<Leguma> toWrite)
        {
            try
            {
                if (toWrite.Count() < 0)
                    throw new MyException("Nothing to write!");
                repo.writeToFile(toWrite);
            }
            catch (IOException)
            {

            }
            catch (MyException)
            {

            }
        }

        public LinkedList<Leguma> whatToWrite()
        {
            try
            {
                LinkedList<Leguma> copy = new LinkedList<Leguma>(repo.getElements());
                LinkedList<Leguma> result = new LinkedList<Leguma>();

                foreach (Leguma elem in copy){
                    if ("Vinete".Equals(elem.getMyClass()) && (elem.getWeight() > 0.2))
                    {
                        result.AddLast(elem);
                    }
                }

                return result;

            }
            catch (NullReferenceException)
            {

            }

            return null;

        }

    }
}

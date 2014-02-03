using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Exam.Repository;
using Exam.Model;
using System.IO;
using Exam.Utils;

namespace Exam.Controller
{
    class Controller
    {
        private RepoInterface<Transaction> repo;
        private Validator val;

        public int errorCounter;

        public Controller(RepoInterface<Transaction> repo, Validator val)
        {
            this.repo = repo;
            this.val = val;
        }


        public IList<Transaction> getAllTransactionObjects()
        {
            IList<Transaction> transactions = this.repo.getAllElements();
            List<Transaction> list = new List<Transaction>();

            foreach (Transaction trans in transactions)
            {
                list.Add(trans);
            }

            return list;
        }

        public void readFromFile(String fileName)
        {
            StreamReader sr = new StreamReader(fileName);
            String line;
            String[] tokens;
            errorCounter = 0;

            List<Transaction> newList = new List<Transaction>();
            try
            {
                while ((line = sr.ReadLine()) != null)
                {
                    tokens = line.Split(new String[] { ";" }, StringSplitOptions.None);

                    if ("Flat".Equals(tokens[0]))
                    {
                        if (tokens.Length != 6)
                        {
                            throw new MyException("Wrong number of arguments!");
                        }
                        Flat flat = new Flat(Convert.ToInt32(tokens[2]), tokens[3], Convert.ToInt32(tokens[1]));
                        Transaction trans = new Transaction(flat, Convert.ToInt32(tokens[4]), Convert.ToInt32(tokens[5]));
                        val.validateTransaction(trans);
                        newList.Add(trans);
                    }
                    else if ("House".Equals(tokens[0]))
                    {
                        if (tokens.Length != 7)
                        {
                            throw new MyException("Wrong number of arguments!");
                        }
                        House house = new House(Convert.ToInt32(tokens[3]), tokens[4], Convert.ToInt32(tokens[2]), Convert.ToInt32(tokens[1]));
                        Transaction trans = new Transaction(house, Convert.ToInt32(tokens[5]), Convert.ToInt32(tokens[6]));
                        val.validateTransaction(trans);
                        newList.Add(trans);
                    }
                    else if ("CommercialSpace".Equals(tokens[0]))
                    {
                        if (tokens.Length != 5)
                        {
                            throw new MyException("Wrong number of arguments!");
                        }
                        CommercialSpace commercialSpace = new CommercialSpace(Convert.ToInt32(tokens[1]), tokens[2]);
                        Transaction trans = new Transaction(commercialSpace, Convert.ToInt32(tokens[3]), Convert.ToInt32(tokens[4]));
                        val.validateTransaction(trans);
                        newList.Add(trans);
                    }
                    else
                    {
                        throw new MyException("Error reading from file!");
                    }
                }

            }
            catch (FormatException ex)
            {
                errorCounter++;
                throw new MyException(ex.Message);
            }
            catch (MyException ex)
            {
                errorCounter++;
                throw new MyException(ex.getMessage());
            }


            this.repo.replaceContent(newList);
            sr.Close();
        }
    }
}

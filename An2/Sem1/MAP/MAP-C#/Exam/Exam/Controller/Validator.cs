using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Exam.Repository;
using Exam.Model;
using Exam.Utils;

namespace Exam.Controller
{
    class Validator
    {
        private RepoInterface<Transaction> repo;
        private Transaction transaction;

        public Validator(RepoInterface<Transaction> repo)
        {
            this.repo = repo;
        }

        private void validateZone()
        {
            if (!((transaction.property.zone == "Z1") || (transaction.property.zone == "Z2")
                || (transaction.property.zone == "Z3") || (transaction.property.zone == "Z4")))
            {
                throw new MyException("Invalid zone!");
            }

        }

        private void validateNumbers()
        {
            if (transaction.property.surface < 0)
            {
                throw new MyException("Invalid numbers");
            }

            if (transaction.property.GetType().Name == "House")
            {
                if ((((House)transaction.property).noOfFloors < 0) || (((House)transaction.property).noOfRooms < 0))
                {
                    throw new MyException("Invalid numbers");
                }
            }

            if (transaction.property.GetType().Name == "Flat")
            {
                if (((Flat)transaction.property).noOfRooms < 0)
                {
                    throw new MyException("Invalid numbers");
                }
            }
        }

        public void validateTransaction(Transaction transaction)
        {
            this.transaction = transaction;

            this.validateZone();
            this.validateNumbers();
        }

    }
}

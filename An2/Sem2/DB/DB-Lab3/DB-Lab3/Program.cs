using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data.SqlClient;
using System.Threading;

namespace DB_Lab3
{
    class Program
    {

        private static SqlConnection sqlConnection = new SqlConnection("Data Source=(local);Initial Catalog=Sports Manager;Integrated Security=SSPI;");

        static void Main(string[] args)
        {
            sqlConnection.Open();

            Thread tran2 = new Thread(Thread2);
            tran2.Start();

            int result = Transaction1();
            if (result < 0)
            {
                Transaction1();
            }
 
            System.Console.Read();

        }

        static void Thread2()
        {
            int result = Transaction2();
            if (result < 0)
            {
                Transaction2();
            }
        }


        static int Transaction1()
        {
           
            SqlCommand sqlCommand = new SqlCommand("begin tran; update Players_List set Name='Andrew' where ID=1; waitfor delay '00:00:05'; update Player_Trophies set MVP=2 where ID=1; rollback tran", sqlConnection);
            int result = sqlCommand.ExecuteNonQuery();
            Console.WriteLine("Thread1 result: {0}", result);

            return result;
        }


        static int Transaction2()
        {
            SqlCommand sqlCommand = new SqlCommand("begin tran; update Player_Trophies set MVP=2 where ID=1; waitfor delay '00:00:05'; update Players_List set Name='Andrew' where ID=1; rollback tran", sqlConnection);
            int result = sqlCommand.ExecuteNonQuery();
            Console.WriteLine("Thread2 result: {0}", result);

            return result;
        }

    }
}

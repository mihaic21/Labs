using MAP_Lab9.Model;
using MAP_Lab9.Controller;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MAP_Lab9.UI
{
    class StudentsObserver : IObserver<IDictionary<int, Student>>
    {
        private String comparator;

        private IDisposable unsubscriber;
                        public StudentsObserver (String comparator)
                        {
                                this.comparator = comparator;
                        }

                        public virtual void Subscribe(Controller.Controller provider) {
                                this.unsubscriber = provider.Subscribe (this);
                        }

                        public virtual void OnNext(IDictionary<int, Student> arg) {
                                foreach (Student student in arg.Values) {
                                        if ("<".Equals(this.comparator)) {
                                                if (student.average() < 5) {
                                                        System.Console.WriteLine (student.ToString() + "     < 5");
                                                }
                                        } else {
                                                if (student.average() >= 5) {
                                                        System.Console.WriteLine (student.ToString() + "     >= 5");
                                                }
                                        }
                                }
                        }

                        public virtual void OnCompleted() {

                        }

                        public virtual void OnError(Exception e) {

                        }

                        public virtual void Unsubscribe() {
                                this.unsubscriber.Dispose ();
                        }
                }
    }


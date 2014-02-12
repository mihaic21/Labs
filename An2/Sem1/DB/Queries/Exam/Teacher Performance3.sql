create view listAllGrades
as
	select T.TeacherName, C.CategoryName as QuestionCategory, sum(TeacherQuestionValue.value) /
		(select COUNT (Q.QuestionID) from Questions Q where Q.CategoryID = C.CategoryID) as Grade
		from Teachers T, Questions Q, Categories C, TeacherQuestionValue
		where T.TeacherID = TeacherQuestionValue.TeacherID and Q.QuestionID = TeacherQuestionValue.QuestionID and Q.CategoryID = C.CategoryID
		group by T.TeacherName, C.CategoryName, C.CategoryID
		


select * from listAllGrades
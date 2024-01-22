# NeuKaB: A Gradebook Chatbot for Teachers
Welcome to NeuKab, a chatbot to assist teachers with managing student grades.

This is a prototype of a CRUD (create, read, update, delete) application.

The program starts by asking the ID of the current teacher (user). If the ID is not in the system, they cannot access the gradebook. In practice, an administrator would need to set up the teacher's account.

Then, the user provides the ID of the student. Similarly, if no such ID is in the database, the student's information would need to be added by an administrator.

The teacher is then prompted to create, update, delete, or view all grades for the student.

For this prototype, please use these IDS:
1. Teacher ID: 456
2. Student ID: 123
3. Class ID: 999

I chose to create this chatbot to simulate a practical problem and solution. Tracking grades is a common issue in schools and I've witnessed the frustration teachers have when interacting with different systems. A chatbot could be a more user-friendly approach.

Future steps include setting up a live database, creating an admin userface, and setting up a UI for teachers. Teachers may also appreciate features like creating groups for collaborative projects or calculating hypothetical grades. There could also be a student-facing version that is view only.

If I pursued creating this for my school, I would want to hold focus groups with teachers and AB test to see what they need and like most. Feedback is important to make sure this tool is actually helpful and being used.
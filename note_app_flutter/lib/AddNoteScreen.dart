import 'package:flutter/material.dart';

import 'database/NoteDao.dart';
import 'model/Note.dart';

class AddNoteScreen extends StatefulWidget {

  List<Note> notes;
  AddNoteScreen({this.notes});
  
  @override
  State<AddNoteScreen> createState() => AddNoteState();
}

class AddNoteState extends State<AddNoteScreen> {

  TextEditingController controllerNoteTitle = TextEditingController();
  TextEditingController controllerNoteContent = TextEditingController();

  NoteDao noteDao;

  @override
  Widget build(BuildContext context) {
    noteDao = ModalRoute.of(context).settings.arguments as NoteDao;
    return createFormAddNote(context);
  }
  
  Widget createFormAddNote(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        body: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Container(
              padding: EdgeInsets.all(15),
              child: Text(
                'Add Note',
                style: TextStyle(
                    fontSize: 25,
                    color: Colors.black,
                    fontWeight: FontWeight.bold),
                )
            ),
            Container(
              padding: EdgeInsets.all(15),
              child: TextField(
                decoration: InputDecoration(
                    labelText: 'Note Title',
                    labelStyle: TextStyle(fontSize: 20.0)
                ),
                controller: controllerNoteTitle,
              ),
            ),
            Container(
              padding: EdgeInsets.all(15),
              child: TextField(
                  decoration: InputDecoration(
                      labelText: 'Note Content',
                      labelStyle: TextStyle(fontSize: 20.0)
                  ),
                  controller: controllerNoteContent
              )
            ),
            ElevatedButton(
                onPressed: () => insertNoteToDatabase(context),
                child: Text('Add Note')
            )
          ]
        ),
        backgroundColor: Colors.yellow,
      )
    );
  }

  @override
  void dispose() {
    controllerNoteTitle.dispose();
    controllerNoteContent.dispose();
    super.dispose();
  }

  insertNoteToDatabase(BuildContext context) async {
    String title = controllerNoteTitle.text;
    String content = controllerNoteContent.text;
    final note = Note(id: null,noteTitle: title, noteContent: content);
    await noteDao.insertNote(note);
    Navigator.pushNamedAndRemoveUntil(context, '/listNoteScreen', (route) => false);
  }

}
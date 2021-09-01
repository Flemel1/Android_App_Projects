import 'package:flutter/material.dart';
import 'package:note_app_flutter/database/NoteDao.dart';
import 'package:note_app_flutter/model/Note.dart';
import 'package:sqflite/sqflite.dart';
import 'package:flutter_slidable/flutter_slidable.dart';

class ListNoteScreen extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => ListNoteState();
}

class ListNoteState extends State<ListNoteScreen> {
  Future<Database> database;
  List<Note> notes = [];
  NoteDao noteDao = NoteDao();

  @override
  void initState() {
    super.initState();
    initNotesFromDatabase();
  }

  @override
  Widget build(BuildContext context) => createListOfNotes(context);

  Widget createListOfNotes(BuildContext context) {
    if (notes.isNotEmpty) {
      return SafeArea(
        child: Scaffold(
          body: ListView.separated(
            padding: EdgeInsets.symmetric(vertical: 15),
            itemBuilder: (context, index) {
              final note = notes[index];
              return Slidable(
                actionPane: SlidableScrollActionPane(),
                actions: [
                  IconSlideAction(
                    caption: 'Delete',
                    color: Colors.red,
                    icon: Icons.delete,
                    onTap: () => deleteNoteFromDatabase(context, note.id),
                  ),
                  IconSlideAction(
                    caption: 'Cancel',
                    color: Colors.blue,
                    icon: Icons.cancel,
                    closeOnTap: true,
                  ),
                ],
                actionExtentRatio: 1 / 3,
                child: Container(
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.stretch,
                    children: [
                      Text(
                        note.noteTitle,
                        style: TextStyle(
                            color: Colors.black,
                            fontSize: 20,
                            fontWeight: FontWeight.bold),
                      ),
                      Text(
                        note.noteContent,
                        style: TextStyle(color: Colors.black, fontSize: 15),
                      ),
                    ],
                  ),
                  margin: EdgeInsets.symmetric(horizontal: 15),
                  padding: EdgeInsets.all(15),
                  decoration: BoxDecoration(
                    color: Colors.white,
                    boxShadow: [
                      BoxShadow(
                        color: Colors.grey,
                        offset: Offset(6, 6),
                        blurRadius: 10,
                      ),
                    ],
                    borderRadius: BorderRadius.circular(10),
                  ),
                ),
              );
            },
            separatorBuilder: (BuildContext context, int index) {
              return Divider();
            },
            itemCount: notes.length,
          ),
          floatingActionButton: FloatingActionButton(
            child: Icon(Icons.add),
            backgroundColor: Colors.blue,
            onPressed: () {
              Navigator.pushNamed(context, '/listNoteScreen/addScreen', arguments: noteDao);
            },
          ),
          backgroundColor: Colors.yellow,
        ),
      );
    }
    return SafeArea(
        child: Scaffold(
      body: Container(
          alignment: AlignmentDirectional.topCenter,
          padding: EdgeInsets.symmetric(vertical: 15),
          child: Text('Notes is empty',
              style: TextStyle(
                  color: Colors.black,
                  fontWeight: FontWeight.bold,
                  fontSize: 25))),
      floatingActionButton: FloatingActionButton(
        child: Icon(Icons.add),
        backgroundColor: Colors.blue,
        onPressed: () {
          Navigator.pushNamed(context, '/listNoteScreen/addScreen', arguments: noteDao);
        },
      ),
      backgroundColor: Colors.yellow,
    ));
  }

  void initNotesFromDatabase() async {
    var _notes = await noteDao.fetchNotes();
    setState(() {
      notes = _notes;
    });
  }

  deleteNoteFromDatabase(BuildContext context, int id) async {
    var result = await noteDao.deleteNote(id);
    if (result >= 1) {
      final text = 'Successfully Delete Item';
      showSnackbar(context, text);
      initNotesFromDatabase();
    } else {
      final text = 'Failed Delete Item';
      showSnackbar(context, text);
    }
  }

  void showSnackbar(BuildContext context, String text) {
    ScaffoldMessenger.of(context)
      ..hideCurrentSnackBar()
      ..showSnackBar(SnackBar(content: Text(text)));
  }
}

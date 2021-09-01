import 'package:note_app_flutter/model/Note.dart';
import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';

class NoteDao {
  static Future<Database> database;

  Future<Database> getDatabase() async {
    if(database != null) {
      return database;
    }
    database = initDatabase();
    return database;
  }

  Future<Database> initDatabase() async {
    return openDatabase(
      join(await getDatabasesPath(), 'note_database.db'),
      onCreate: (db, version) {
        return db.execute(
          "CREATE TABLE notes(id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT)",
        );
      },
      version: 1,
    );
  }

  Future<List<Note>> fetchNotes() async {
    final Database db = await getDatabase();
    final List<Map<String, dynamic>> maps = await db.query('notes');
    return List.generate(maps.length, (i) {
      return Note(
        id: maps[i]['id'],
        noteTitle: maps[i]['title'],
        noteContent: maps[i]['content'],
      );
    });
  }

  Future<int> insertNote(Note note) async {
    final Database db = await getDatabase();
    var id = await db.insert(
      'notes',
      note.toMap(),
      conflictAlgorithm: ConflictAlgorithm.ignore,
    );
    return id;
  }

  Future<int> deleteNote(int id) async {
    final Database db = await getDatabase();
    var result = await db.delete(
      'notes',
      where: "id = ?",
      whereArgs: [id],
    );
    return result;
  }
}
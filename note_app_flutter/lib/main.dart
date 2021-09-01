import 'package:flutter/material.dart';
import 'package:note_app_flutter/view/ListNoteScreen.dart';
import 'AddNoteScreen.dart';
import 'view/HomePage.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'Flutter Demo',
        theme: ThemeData(),
        initialRoute: '/',
        routes: {
          '/': (context) => HomePage(),
          '/listNoteScreen': (context) => ListNoteScreen(),
          '/listNoteScreen/addScreen': (context) => AddNoteScreen()
        });
  }
}
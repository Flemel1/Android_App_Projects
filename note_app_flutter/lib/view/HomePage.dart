import 'package:flutter/material.dart';

class HomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) => createHomeScreen(context);

  Widget createHomeScreen(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        backgroundColor: Colors.yellow,
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Text('Welcome to Note App',
                  style: TextStyle(color: Colors.black, fontSize: 25)),
              Text('Swipe Right to Delete Note',
                  style: TextStyle(color: Colors.black, fontSize: 25)),
              ElevatedButton(
                onPressed: () {
                  Navigator.pushNamedAndRemoveUntil(context, '/listNoteScreen', (route) => false);
                },
                child: Text('Start Now'),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

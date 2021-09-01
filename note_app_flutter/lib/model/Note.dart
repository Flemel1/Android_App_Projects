class Note {

  final int id;
  final String noteTitle;
  final String noteContent;

  Note({this.id,this.noteTitle, this.noteContent});

  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'title': noteTitle,
      'content': noteContent
    };
  }

}

var notes = [
  Note(
      noteTitle: 'Note 1',
      noteContent: 'Ini adalah isi content dari note yang berjudul Note 1'),
  Note(
      noteTitle: 'Note 2',
      noteContent: 'Hallo world selamat datang di aplikasi note untuk flutter'),
  Note(
      noteTitle: 'Note 3',
      noteContent:
          'Content pada Note 3 ini mengandung catatan yang sangat penting dimasa depan'),
  Note(
      noteTitle: 'Note 4',
      noteContent:
      'Note 4 ini merupakan catatan hari ini'),
  Note(
      noteTitle: 'Note 5',
      noteContent:
      'Mengumpulkan jawaban UTS dan UAS pada semester kemaren'),
  Note(
      noteTitle: 'Note 6',
      noteContent:
      'Catatan ini berisi jadwal UAS semester 6'),
];

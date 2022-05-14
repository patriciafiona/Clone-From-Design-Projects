import 'package:flutter/material.dart';

class SongScreen extends StatefulWidget {
  final String songUrl;
  final String title;
  final String singer;
  final String albumImg;
  final String playlistName;

  SongScreen({
    required this.singer,
    required this.songUrl,
    required this.title,
    required this.albumImg,
    required this.playlistName
  });

  @override
  State<SongScreen> createState() => _SongScreenState();
}

class _SongScreenState extends State<SongScreen> {
  @override
  Widget build(BuildContext context) {
    final mediaQuery = MediaQuery.of(context);

    return Scaffold(
        extendBodyBehindAppBar: true,
        appBar: AppBar(
          centerTitle: true,
          elevation: 0,
          actions: <Widget>[
            Padding(
                padding: EdgeInsets.only(right: 20.0),
                child: GestureDetector(
                  onTap: () {},
                  child: Icon(
                      Icons.more_vert
                  ),
                )
            ),
          ],
          title: Column(
              children: [
                Text(
                  'PLAYING FROM PLAYLIST',
                  style: TextStyle(
                    fontFamily: 'Gotham',
                    fontWeight: FontWeight.w300,
                    color: Colors.white,
                    fontSize: 14,
                  ),
                  textAlign: TextAlign.center,
                ),
                Text(
                  widget.playlistName,
                  style: TextStyle(
                    fontFamily: 'Gotham',
                    fontWeight: FontWeight.w500,
                    color: Colors.white,
                    fontSize: 14,
                  ),
                  textAlign: TextAlign.center,
                )
              ]
          ),
          backgroundColor: Colors.transparent,
        ),
        body: Container(
            decoration: const BoxDecoration(
                gradient: LinearGradient(
                  begin: Alignment.topCenter,
                  end: Alignment.bottomCenter,
                  colors: [
                    Color.fromRGBO(153, 87, 3, 1),
                    Colors.black87,
                  ],
                  stops: [
                    0.1,
                    0.4
                  ],
                )
            ),
            child: Container(
                padding: EdgeInsets.only(top: 80, right: 20, left: 20, bottom: 30),
                width: double.infinity,
                child: SingleChildScrollView(
                  padding: EdgeInsets.all(20),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: [
                      Container(
                        width: 400,
                        height: 400,
                        child: Image.network(
                          widget.albumImg,
                          fit: BoxFit.cover,
                        ),
                        decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(5.0),
                          boxShadow: [
                            BoxShadow(
                              color: Colors.black54,
                              offset: Offset(0.0, 1.0), //(x,y)
                              blurRadius: 6.0,
                            ),
                          ],
                        ),
                      )
                    ]
                  )
                )
            )
        ),
    );
  }
}

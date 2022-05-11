import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/material.dart';

class PlaylistScreen extends StatefulWidget {
  static const routeName = '/playlist_screen';

  final String backgroundUrl;
  final String name;
  final String description;
  final int likes;
  final String singer;

  PlaylistScreen({
    required this.backgroundUrl,
    required this.name,
    required this.description,
    required this.likes,
    required this.singer
  });

  @override
  State<PlaylistScreen> createState() => _PlaylistScreenState();
}

class _PlaylistScreenState extends State<PlaylistScreen> {
  var top = 0.0;
  var myOpacity;

  bool _isDownload = false;

  @override
  Widget build(BuildContext context) {
    final mediaQuery = MediaQuery.of(context);

    return Scaffold(
      extendBodyBehindAppBar: true,
      appBar: AppBar(
        centerTitle: true,
        elevation: 0,
        title: LayoutBuilder(
          builder: (BuildContext context, BoxConstraints constraints) {
            top = constraints.biggest.height;
            myOpacity = (top - MediaQuery
                .of(context)
                .padding
                .top + kToolbarHeight) / 1000;
            return AnimatedOpacity(
                duration: Duration(milliseconds: 250),
                opacity: 1.0,
                child: Text(widget.name, style: Theme.of(context).textTheme.headline6, textAlign: TextAlign.center,)
            );
          },
        ),
        backgroundColor: Colors.transparent,
      ),
      body: Stack(
        children: [
          Container(
            height: mediaQuery.size.height,
            width: double.infinity,
            color: Colors.black87,
          ),
          ShaderMask(
            shaderCallback: (rect) {
              return LinearGradient(
                begin: Alignment.topCenter,
                end: Alignment.bottomCenter,
                colors: [Colors.black87, Colors.transparent],
              ).createShader(Rect.fromLTRB(0, 0, rect.width, rect.height));
            },
            blendMode: BlendMode.dstIn,
            child: Container(
              height: 400,
              width: double.infinity,
              child: Image.network(
                widget.backgroundUrl,
                fit: BoxFit.cover,
              ),
            ),
          ),
          SingleChildScrollView(
            padding: EdgeInsets.only(
                top: 300),
            child: Container(
              decoration: const BoxDecoration(
                  gradient: LinearGradient(
                    begin: Alignment.topCenter,
                    end: Alignment.bottomCenter,
                    colors: [
                      Colors.transparent,
                      Colors.black87,
                    ],
                    stops: [
                      0.0,
                      0.1
                    ],
                  )
              ),
              padding: EdgeInsets.symmetric(vertical: 10, horizontal: 20),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Container(
                    alignment: Alignment.center,
                    child: ElevatedButton(
                      onPressed: () {},
                      child: const Text('PLAY'),
                      style: ElevatedButton.styleFrom(
                        padding: EdgeInsets.symmetric(vertical: 20, horizontal: 30),
                        primary: const Color.fromRGBO(98, 205, 107, 1),
                        textStyle: const TextStyle(
                            fontFamily: 'Gotham',
                            fontWeight: FontWeight.bold,
                            fontSize: 18
                        ),
                        minimumSize: Size(
                            (mediaQuery.size.width - mediaQuery.padding.horizontal) * 0.35, 50
                        ),
                        shape: const StadiumBorder(),
                      ),
                    ),
                  ),
                  SizedBox(height: 10),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.start,
                    children: [
                      Expanded(child:
                        Text(
                          'Download',
                          style: TextStyle(
                              fontFamily: 'Gotham',
                              fontWeight: FontWeight.w500,
                              fontSize: 20,
                              color: Colors.white
                          ),
                          textAlign: TextAlign.start,
                        ),
                      ),
                      Switch(
                          activeColor: Colors.green,
                          value: _isDownload,
                          onChanged: (value) => setState(() {
                            _isDownload = value;
                          })
                      ),
                    ]
                  ),
                  SizedBox(height: 10),
                  Container(
                    height: mediaQuery.size.width * 1.5,
                    child: StreamBuilder(
                      stream: FirebaseFirestore.instance.collection('songs').where('singer', isEqualTo: widget.singer).snapshots(),
                      builder: (ctx, AsyncSnapshot<QuerySnapshot> dataSnapshot) {
                        if (dataSnapshot.connectionState ==
                            ConnectionState.waiting) {
                          return Center(
                            child: CircularProgressIndicator(),
                          );
                        }
                        final songDocs = dataSnapshot.data?.docs;
                        return ListView.builder(
                            physics: const NeverScrollableScrollPhysics(),
                            padding: EdgeInsets.zero,
                            itemCount: songDocs?.length,
                            itemBuilder: (BuildContext context, int index) {
                              var playlist_data = songDocs?[index].data() as Map<String, dynamic>;
                              return StreamBuilder(
                                  stream: FirebaseFirestore.instance
                                      .collection('albums')
                                      .where(
                                      'name', isEqualTo: playlist_data['album'])
                                      .snapshots(),
                                  builder: (ctx, AsyncSnapshot<
                                      QuerySnapshot> dataSnapshot) {
                                    if (dataSnapshot.connectionState ==
                                        ConnectionState.waiting) {
                                      return Center(
                                        child: CircularProgressIndicator(),);
                                    }
                                    final albumDocs = dataSnapshot.data?.docs;
                                    var album_data = albumDocs?[0]
                                        .data() as Map<String, dynamic>;
                                    return ListTile(
                                      leading: Image.network(
                                        album_data['imageUrl'],
                                        fit: BoxFit.cover,
                                      ),
                                      title: Text(
                                        playlist_data['title'],
                                        overflow: TextOverflow.ellipsis,
                                        style: TextStyle(
                                            fontFamily: 'Gotham',
                                            fontWeight: FontWeight.bold,
                                            fontSize: 14,
                                            color: Colors.white
                                        ),
                                      ),
                                      subtitle: Text(
                                        '${playlist_data['singer']} • ${playlist_data['album']}',
                                        overflow: TextOverflow.ellipsis,
                                        style: TextStyle(
                                            fontFamily: 'Gotham',
                                            fontWeight: FontWeight.bold,
                                            fontSize: 14,
                                            color: Colors.grey
                                        ),
                                      ),
                                      trailing: Container(
                                        margin: EdgeInsets.only(top: 10),
                                        child: TextButton(
                                          child: Icon(Icons.more_vert),
                                          onPressed: () {},
                                          style: TextButton.styleFrom(
                                            primary: Colors.white,
                                          ),
                                        ),
                                      ),
                                    );
                                  }
                              );
                            }
                        );
                      }
                    ),
                  ),
                ],
              )
            )
          )
        ],
      ),
    );
  }
}

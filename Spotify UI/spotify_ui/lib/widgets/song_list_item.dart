import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/material.dart';
import 'package:spotify_ui/screens/song_screen/song_screen.dart';

class SongListItem extends StatelessWidget {
  final double height;
  final String singer;
  final String playlistName;

  const SongListItem({required this.height, required this.singer, required this.playlistName});

  @override
  Widget build(BuildContext context) {
    return Container(
      height: height,
      child: StreamBuilder(
          stream: FirebaseFirestore.instance.collection('songs').where('singer', arrayContains: singer).snapshots(),
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
                          onTap: () {
                            if(playlist_data['songUrl'] != null){
                              Navigator.push(
                                  context,
                                  MaterialPageRoute(builder: (context) => SongScreen(
                                    playlistName: playlistName,
                                    songUrl: playlist_data['songUrl'],
                                    title: playlist_data['title'],
                                    albumImg: album_data['imageUrl'],
                                    singer: singer)
                                  )
                              );
                            }
                          },
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
                            '${playlist_data['singer'].join(', ')} • ${playlist_data['album']}',
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
    );
  }
}

import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/material.dart';
import 'package:spotify_ui/widgets/album_horizontal_item.dart';
import 'package:spotify_ui/widgets/artist_horizontal_item.dart';

import '../../../../widgets/playlist_horizontal_item.dart';

class MusicTabScreen extends StatefulWidget {
  const MusicTabScreen({Key? key}) : super(key: key);

  @override
  State<MusicTabScreen> createState() => _MusicTabScreenState();
}

class _MusicTabScreenState extends State<MusicTabScreen> with SingleTickerProviderStateMixin{
  late TabController _tabController;

  @override
  void initState() {
    super.initState();
    _tabController = TabController(length: 3, vsync: this);
    _tabController.addListener(_handleTabSelection);
  }

  void _handleTabSelection() {
    setState(() {
    });
  }

  @override
  Widget build(BuildContext context) {
    final mediaQuery = MediaQuery.of(context);

    return Container(
      width: double.infinity,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          TabBar(
            isScrollable: true,
            controller: _tabController,
            unselectedLabelColor: Colors.grey,
            labelStyle: TextStyle(fontSize: 22.0, fontWeight: FontWeight.bold),
            unselectedLabelStyle: TextStyle(fontSize: 20.0, fontWeight: FontWeight.bold),
            indicatorSize: TabBarIndicatorSize.label,
            indicator: UnderlineTabIndicator(
              borderSide: BorderSide(
                  width: 3, color: Color.fromRGBO(98, 205, 107, 1),
              ),
            ),
            tabs: [
              Tab(
                text: 'Playlists',
              ),
              Tab(
                text: 'Artist',
              ),
              Tab(
                text: 'Album',
              ),
            ],
          ),
          Container(
            height: mediaQuery.size.height * 0.9,
            child: new TabBarView(
              controller: _tabController,
              children: <Widget>[
                Container(
                  height: double.infinity,
                  padding: EdgeInsets.only(bottom: 200),
                  child: StreamBuilder(
                    stream: FirebaseFirestore.instance.collection('jump_back_in').snapshots(),
                    builder: (ctx, AsyncSnapshot<QuerySnapshot> dataSnapshot) {
                      if (dataSnapshot.connectionState == ConnectionState.waiting) {
                        return Center(
                          child: CircularProgressIndicator(),
                        );
                      }
                      final jumpBackInDocs = dataSnapshot.data?.docs;
                      return ListView.builder(
                          shrinkWrap: true,
                          scrollDirection: Axis.vertical,
                          itemCount: jumpBackInDocs?.length,
                          itemBuilder: (BuildContext context, int index) {
                            var jump_back_in_data = jumpBackInDocs?[index].data() as Map<String, dynamic>;
                            return PlaylistHorizontalItem(playlist: jump_back_in_data);
                          }
                      );
                    }
                  )
                ),
                Container(
                  height: double.infinity,
                  padding: EdgeInsets.only(bottom: 200),
                  child: StreamBuilder(
                    stream: FirebaseFirestore.instance.collection('artists').snapshots(),
                    builder: (ctx, AsyncSnapshot<QuerySnapshot> dataSnapshot) {
                      if (dataSnapshot.connectionState == ConnectionState.waiting) {
                        return Center(
                          child: CircularProgressIndicator(),
                        );
                      }
                      final artistsDocs = dataSnapshot.data?.docs;
                      return ListView.builder(
                        shrinkWrap: true,
                        scrollDirection: Axis.vertical,
                        itemCount: artistsDocs?.length,
                        itemBuilder: (BuildContext context, int index) {
                          var charts_data = artistsDocs?[index].data() as Map<String, dynamic>;
                          return ArtistHorizontalItem(data: charts_data);
                        }
                      );
                    }
                  )
                ),
                Container(
                    height: double.infinity,
                    padding: EdgeInsets.only(bottom: 200),
                    child: StreamBuilder(
                        stream: FirebaseFirestore.instance.collection('albums').snapshots(),
                        builder: (ctx, AsyncSnapshot<QuerySnapshot> dataSnapshot) {
                          if (dataSnapshot.connectionState == ConnectionState.waiting) {
                            return Center(
                              child: CircularProgressIndicator(),
                            );
                          }
                          final albumsDocs = dataSnapshot.data?.docs;
                          return ListView.builder(
                              shrinkWrap: true,
                              scrollDirection: Axis.vertical,
                              itemCount: albumsDocs?.length,
                              itemBuilder: (BuildContext context, int index) {
                                var albums_data = albumsDocs?[index].data() as Map<String, dynamic>;
                                return AlbumHorizontalItem(data: albums_data);
                              }
                          );
                        }
                    )
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}

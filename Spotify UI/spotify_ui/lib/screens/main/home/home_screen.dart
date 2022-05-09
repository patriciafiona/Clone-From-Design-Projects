import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/material.dart';
import 'package:spotify_ui/screens/settings/settings_screen.dart';
import 'package:spotify_ui/widgets/artist_item.dart';
import 'package:spotify_ui/widgets/chart_item.dart';
import 'package:spotify_ui/widgets/last_seen_item.dart';
import 'package:spotify_ui/widgets/playlist_item.dart';
import 'package:spotify_ui/widgets/radio_item.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({Key? key}) : super(key: key);

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  String _greetingText() {
    var hour = DateTime.now().hour;
    if (hour < 12) {
      return 'Good Morning';
    }
    if (hour < 17) {
      return 'Good Afternoon';
    }
    return 'Good Evening';
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Container(
        decoration: const BoxDecoration(
        gradient: RadialGradient(
          center: Alignment(-0.5, -1.5), // near the top right
          radius: 0.9,
          stops: <double>[0.2, 1.0],
          colors: [
            Color.fromRGBO(153, 87, 3, 1),
            Colors.black87,
          ],
        )
      ),
      child: Container(
        width: double.infinity,
        child: SingleChildScrollView(
          padding: EdgeInsets.all(20),
          child: Column(
            mainAxisSize: MainAxisSize.min,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Row(
                children: [
                  Spacer(),
                  Container(
                    margin: EdgeInsets.only(top: 10),
                    child: TextButton(
                      child: Icon(Icons.settings),
                      onPressed: () {
                        Navigator.pushNamed(context, SettingsScreen.routeName);
                      },
                      style: TextButton.styleFrom(
                        primary: Colors.white,
                      ),
                    ),
                  ),
                ],
              ),
              Text(
                _greetingText(),
                style: TextStyle(
                  fontFamily: 'Gotham',
                  fontWeight: FontWeight.bold,
                  fontSize: 30,
                  color: Colors.white
                ),
              ),
              SizedBox(height: 10,),
              Container(
                  height: 180,
                  child: StreamBuilder(
                      stream: FirebaseFirestore.instance.collection('last_seen').orderBy('date').snapshots(),
                      builder: (ctx, AsyncSnapshot<QuerySnapshot> dataSnapshot) {
                        if (dataSnapshot.connectionState == ConnectionState.waiting) {
                          return Center(
                            child: CircularProgressIndicator(),
                          );
                        }
                        final lastSeenDocs = dataSnapshot.data?.docs;
                        return ListView.builder(
                            shrinkWrap: true,
                            scrollDirection: Axis.horizontal,
                            itemCount: lastSeenDocs?.length,
                            itemBuilder: (BuildContext context, int index) {
                              var last_seen_data = lastSeenDocs?[index].data() as Map<String, dynamic>;
                              return LastSeenItem(data: last_seen_data);
                            }
                        );
                      }
                  )
              ),
              SizedBox(height: 10,),
              Text(
                'Jump back in',
                style: TextStyle(
                    fontFamily: 'Gotham',
                    fontWeight: FontWeight.bold,
                    fontSize: 24,
                    color: Colors.white
                ),
              ),
              SizedBox(height: 10,),
              Container(
                height: 180,
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
                      scrollDirection: Axis.horizontal,
                      itemCount: jumpBackInDocs?.length,
                      itemBuilder: (BuildContext context, int index) {
                        var jump_back_in_data = jumpBackInDocs?[index].data() as Map<String, dynamic>;
                        return PlaylistItem(playlist: jump_back_in_data);
                      }
                    );
                  }
                )
              ),
              SizedBox(height: 10,),
              Row(
                children: [
                  Container(
                      width: 200,
                      child: Image.asset('assets/images/indienesia_album.jpeg'),
                  ),
                  SizedBox(width: 10,),
                  Expanded(
                      child: Text(
                        'Lagu terfavorit dari Perunggu, Jason Ranti, Foutwnty & lainnya!',
                        style: TextStyle(
                            fontFamily: 'Gotham',
                            fontWeight: FontWeight.bold,
                            fontSize: 24,
                            color: Colors.white
                        ),
                      )
                  )
                ],
              ),
              SizedBox(height: 10,),
              Text(
                'Charts',
                style: TextStyle(
                    fontFamily: 'Gotham',
                    fontWeight: FontWeight.bold,
                    fontSize: 24,
                    color: Colors.white
                ),
              ),
              SizedBox(height: 10,),
              Container(
                  height: 230,
                  child: StreamBuilder(
                      stream: FirebaseFirestore.instance.collection('charts').snapshots(),
                      builder: (ctx, AsyncSnapshot<QuerySnapshot> dataSnapshot) {
                        if (dataSnapshot.connectionState == ConnectionState.waiting) {
                          return Center(
                            child: CircularProgressIndicator(),
                          );
                        }
                        final chartsDocs = dataSnapshot.data?.docs;
                        return ListView.builder(
                            shrinkWrap: true,
                            scrollDirection: Axis.horizontal,
                            itemCount: chartsDocs?.length,
                            itemBuilder: (BuildContext context, int index) {
                              var charts_data = chartsDocs?[index].data() as Map<String, dynamic>;
                              return ChartItem(chart: charts_data);
                            }
                        );
                      }
                  )
              ),
              SizedBox(height: 10,),
              Text(
                'Popular Artist',
                style: TextStyle(
                    fontFamily: 'Gotham',
                    fontWeight: FontWeight.bold,
                    fontSize: 24,
                    color: Colors.white
                ),
              ),
              SizedBox(height: 10,),
              Container(
                  height: 230,
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
                            scrollDirection: Axis.horizontal,
                            itemCount: artistsDocs?.length,
                            itemBuilder: (BuildContext context, int index) {
                              var charts_data = artistsDocs?[index].data() as Map<String, dynamic>;
                              return ArtistItem(data: charts_data);
                            }
                        );
                      }
                  )
              ),
              SizedBox(height: 10,),
              Text(
                'Recommended Radio',
                style: TextStyle(
                    fontFamily: 'Gotham',
                    fontWeight: FontWeight.bold,
                    fontSize: 24,
                    color: Colors.white
                ),
              ),
              SizedBox(height: 3,),
              Text(
                'Non-stop music based on your favorite songs and artists.',
                style: TextStyle(
                    fontFamily: 'Gotham',
                    fontSize: 14,
                    color: Colors.grey
                ),
              ),
              SizedBox(height: 10,),
              Container(
                height: 230,
                child: StreamBuilder(
                    stream: FirebaseFirestore.instance.collection('radios').snapshots(),
                    builder: (ctx, AsyncSnapshot<QuerySnapshot> dataSnapshot) {
                      if (dataSnapshot.connectionState == ConnectionState.waiting) {
                        return Center(
                          child: CircularProgressIndicator(),
                        );
                      }
                      final jumpBackInDocs = dataSnapshot.data?.docs;
                      return ListView.builder(
                          shrinkWrap: true,
                          scrollDirection: Axis.horizontal,
                          itemCount: jumpBackInDocs?.length,
                          itemBuilder: (BuildContext context, int index) {
                            var radio_data = jumpBackInDocs?[index].data() as Map<String, dynamic>;
                            return RadioItem(radio: radio_data);
                          }
                      );
                    }
                )
              ),
            ],),
        )
        )
      ),
    );
  }
}

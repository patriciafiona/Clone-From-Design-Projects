import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:should_rebuild/should_rebuild.dart';
import 'package:spotify_ui/widgets/song_list_item.dart';

class PlaylistScreen extends StatefulWidget {
  static const routeName = '/playlist_screen';

  final String backgroundUrl;
  final String name;
  final String description;
  final int likes;
  final int followers;
  final String singer;

  const PlaylistScreen({
    required this.backgroundUrl,
    required this.name,
    required this.description,
    required this.likes,
    required this.followers,
    required this.singer
  });

  @override
  State<PlaylistScreen> createState() => _PlaylistScreenState();
}

class _PlaylistScreenState extends State<PlaylistScreen> with TickerProviderStateMixin{
  var top = 0.0;
  var myOpacity;

  final number_format = NumberFormat("#,##0");
  bool _isDownload = false;

  ScrollController scrollController = ScrollController();
  double _scrollPosition = 0.0;
  double _appBarTextOpacity = 0.0;
  double _detailTextOpacity = 1.0;
  double _fontTitle = 30.0;
  double _fontSubTitle = 14.0;

  @override
  void initState() {
    scrollController.addListener(() { //listener
      _scrollPosition = scrollController.offset;

      if(_scrollPosition >= 0.0 && _scrollPosition <180.0){
        _appBarTextOpacity = _scrollPosition / 180;
      }

      if(_scrollPosition >= 0.0 && _scrollPosition <200.0){
        _detailTextOpacity = 1 - (_scrollPosition / 200);
        if(_detailTextOpacity >0 && _detailTextOpacity <= 0.25){
          _detailTextOpacity = 0;
        }
      }

      if(_scrollPosition >= 0.0 && _scrollPosition < 140.0){
        _fontTitle = 30 - (_scrollPosition - 120.0);
        if(_fontTitle <= 0 ) {
          _fontTitle = 0.0;
        }else if (_fontTitle >= 30){
          _fontTitle = 30.0;
        }

        _fontSubTitle = _fontTitle - 10;
        print('_fontSubTitle: $_fontSubTitle');
        if(_fontSubTitle <= 0 ) {
          _fontSubTitle = 0.0;
        }else if (_fontSubTitle >= 14){
          _fontSubTitle = 14.0;
        }
      }

      setState(() {
        //update data
      });
    });
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    final mediaQuery = MediaQuery.of(context);

    return Scaffold(
      extendBodyBehindAppBar: true,
      appBar: AppBar(
        centerTitle: true,
        elevation: 0,
        title: AnimatedOpacity(
            duration: Duration(milliseconds: 250),
            opacity: _appBarTextOpacity,
            child: Text(widget.name, style: Theme.of(context).textTheme.headline6, textAlign: TextAlign.center,)
        ),
        backgroundColor: Colors.transparent,
      ),
      body: Stack(
        children: [
          Container(
            height: mediaQuery.size.height,
            width: double.infinity,
            color: Colors.black,
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
          AnimatedOpacity(
            duration: Duration(milliseconds: 250),
            opacity: _detailTextOpacity,
            child: Container(
              padding: EdgeInsets.all(10),
              alignment: Alignment.center,
              height: 400,
              child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Text(
                      widget.name,
                      style: TextStyle(
                          color: Colors.white,
                          fontFamily: 'Gotham',
                          fontWeight: FontWeight.bold,
                          fontSize: _fontTitle
                      ),
                      textAlign: TextAlign.center,
                    ),
                    SizedBox(height: 35,),
                    Text(
                      widget.description,
                      style: TextStyle(
                          color: Colors.white,
                          fontFamily: 'Gotham',
                          fontWeight: FontWeight.bold,
                          fontSize: _fontSubTitle
                      ),
                      textAlign: TextAlign.center,
                    ),
                    SizedBox(height: 10,),
                    Container(
                      alignment: Alignment.center,
                      child: ElevatedButton(
                        onPressed: () {},
                        child: const Text('FOLLOWING'),
                        style: ElevatedButton.styleFrom(
                          padding: EdgeInsets.symmetric(
                              vertical: 7, horizontal: 10),
                          primary: Colors.transparent,
                          textStyle: const TextStyle(
                              fontFamily: 'Gotham',
                              fontWeight: FontWeight.bold,
                              fontSize: 14
                          ),
                          side: const BorderSide(
                            width: 1,
                            color: Color.fromRGBO(98, 205, 107, 1),
                          ),
                          minimumSize: Size(
                              (mediaQuery.size.width -
                                  mediaQuery.padding.horizontal) * 0.25, 20
                          ),
                          shape: const StadiumBorder(),
                        ),
                      ),
                    ),
                    SizedBox(height: 10,),
                    Text(
                      '${number_format.format(widget.followers)} FOLLOWERS',
                      style: TextStyle(
                          color: Colors.grey,
                          fontFamily: 'Gotham',
                          fontWeight: FontWeight.w500,
                          fontSize: 12
                      ),
                      textAlign: TextAlign.center,
                    ),
                  ]),
            ),
          ),
          SingleChildScrollView(
            controller: scrollController,
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
                  const SizedBox(height: 10),
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
                  const SizedBox(height: 10),
                  ShouldRebuild<SongListItem>(
                      shouldRebuild: (oldWidget, newWidget) => oldWidget.singer != newWidget.singer,
                      child: SongListItem(
                          height: mediaQuery.size.width * 1.5,
                          singer: widget.singer,
                          playlistName: widget.name,
                      )
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

import 'package:assets_audio_player/assets_audio_player.dart';
import 'package:flutter/material.dart';
import 'package:marquee/marquee.dart';

class SongScreen extends StatefulWidget {
  final String title;
  final String singer;
  final String albumImg;
  final String playlistName;
  final String length;
  final String fileName;

  SongScreen({
    required this.singer,
    required this.title,
    required this.albumImg,
    required this.playlistName,
    required this.length,
    required this.fileName
  });

  @override
  State<SongScreen> createState() => _SongScreenState();
}

class _SongScreenState extends State<SongScreen> with TickerProviderStateMixin {
  AssetsAudioPlayer assetsAudioPlayer = AssetsAudioPlayer();  // this will create a instance object of a class
  bool isFavorite = false;
  bool isPause = false;
  String currentTime = '0:00';
  var text_controller = TextEditingController();

  @override
  void dispose() {
    text_controller.dispose();
    controller.dispose();
    assetsAudioPlayer.dispose();
    super.dispose();
  }

  void didChangeAppLifecycleState(AppLifecycleState state) {
    if (state == AppLifecycleState.paused) {
      assetsAudioPlayer.stop();
    }
  }

  late AnimationController controller;

  Duration parseDuration(String s) {
    int hours = 0;
    int minutes = 0;
    int micros;
    List<String> parts = s.split(':');
    if (parts.length > 2) {
      hours = int.parse(parts[parts.length - 3]);
    }
    if (parts.length > 1) {
      minutes = int.parse(parts[parts.length - 2]);
    }
    micros = (double.parse(parts[parts.length - 1]) * 1000000).round();
    return Duration(hours: hours, minutes: minutes, microseconds: micros);
  }

  @override
  void initState() {
    assetsAudioPlayer = AssetsAudioPlayer();
    assetsAudioPlayer.open(
      Audio(
        "assets/songs/${widget.fileName}",
        metas: Metas(
          title:  widget.title,
          artist: widget.singer,
          album: widget.playlistName,
          image: MetasImage.network(widget.albumImg), //can be MetasImage.network
        ),
      ),
      autoStart: true,
      showNotification: true,
    );

    controller = AnimationController(
      vsync: this,
      duration: parseDuration(widget.length),
    )..addListener(() {
      setState(() {});
    });
    controller.repeat(reverse: false);

    super.initState();
  }

  String _printDuration(Duration duration) {
    String twoDigits(int n) => n.toString().padLeft(2, "0");
    String twoDigitMinutes = twoDigits(duration.inMinutes.remainder(60));
    String twoDigitSeconds = twoDigits(duration.inSeconds.remainder(60));
    return "$twoDigitMinutes:$twoDigitSeconds";
  }

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
        body: Stack(
        children: [
          Container(
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
          )),
          Container(
            padding: EdgeInsets.only(top: 100, right: 10, left: 10, bottom: 30),
            width: double.infinity,
            child: SingleChildScrollView(
                padding: EdgeInsets.all(20),
                child: Column(
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: [
                      Container(
                        width: (mediaQuery.size.width - mediaQuery.padding.horizontal) * 0.85,
                        height: (mediaQuery.size.width - mediaQuery.padding.horizontal) * 0.85,
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
                      ),
                      SizedBox(height: mediaQuery.size.height * 0.15,),
                      Row(
                        children: [
                          Container(
                              child: Column(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: [
                                  Container(
                                    width: (mediaQuery.size.width - mediaQuery.padding.horizontal) * 0.65,
                                    height: 30,
                                    child: LayoutBuilder(builder: (context, size) {
                                      var span = TextSpan(
                                          text: widget.title,
                                          style: TextStyle(
                                            fontFamily: 'Gotham',
                                            fontWeight: FontWeight.bold,
                                            color: Colors.white,
                                            fontSize: 18,
                                          )
                                      );

                                      // Use a textpainter to determine if it will exceed max lines
                                      var tp = TextPainter(
                                        maxLines: 1,
                                        textAlign: TextAlign.left,
                                        textDirection: TextDirection.ltr,
                                        text: span,
                                      );

                                      // trigger it to layout
                                      tp.layout(maxWidth: size.maxWidth);

                                      // whether the text overflowed or not
                                      var exceeded = tp.didExceedMaxLines;

                                      if(exceeded){
                                        return Marquee(
                                          text: widget.title,
                                          style: TextStyle(
                                            fontFamily: 'Gotham',
                                            fontWeight: FontWeight.bold,
                                            color: Colors.white,
                                            fontSize: 18,
                                          ),
                                          scrollAxis: Axis.horizontal,
                                          crossAxisAlignment: CrossAxisAlignment.start,
                                          blankSpace: 20.0,
                                          velocity: 100.0,
                                          accelerationDuration: Duration(milliseconds: 2500),
                                          accelerationCurve: Curves.linear,
                                          decelerationDuration: Duration(milliseconds: 2300),
                                          decelerationCurve: Curves.easeOut,
                                        );
                                      }

                                      return Text(
                                        widget.title,
                                        style: TextStyle(
                                          fontFamily: 'Gotham',
                                          fontWeight: FontWeight.bold,
                                          color: Colors.white,
                                          fontSize: 20,
                                        ),
                                      );
                                    }),
                                  ),
                                  Text(
                                    widget.singer,
                                    style: TextStyle(
                                      fontFamily: 'Gotham',
                                      fontWeight: FontWeight.w500,
                                      color: Colors.white70,
                                      fontSize: 16,
                                    ),
                                  )
                                ],
                              )
                          ),
                          Spacer(),
                          Container(
                            width: (mediaQuery.size.width - mediaQuery.padding.horizontal) * 0.18,
                            margin: EdgeInsets.only(top: 10),
                            child: FittedBox(
                              child: TextButton(
                                child: isFavorite? Icon(Icons.favorite):
                                Icon(Icons.favorite_border_outlined),
                                onPressed: () {
                                  setState(() {
                                    isFavorite = !isFavorite;
                                  });
                                },
                                style: TextButton.styleFrom(
                                  primary: isFavorite? Colors.red : Colors.white,
                                ),
                              ),
                            ),
                          ),
                        ],
                      ),
                      StreamBuilder(
                          stream: assetsAudioPlayer.currentPosition,
                          builder: (context, asyncSnapshot) {
                            final Object? duration = asyncSnapshot.data;
                            return Column(
                                children: [
                                  LinearProgressIndicator(
                                    value: controller.value,
                                    semanticsLabel: 'Linear progress indicator',
                                  ),
                                  SizedBox(height: 5,),
                                  Row(
                                    children: [
                                      Text(
                                        duration != null ? _printDuration(duration as Duration) : '??:??',
                                        style: TextStyle(
                                          fontFamily: 'Gotham',
                                          fontWeight: FontWeight.w500,
                                          color: Colors.grey,
                                          fontSize: 14,
                                        ),
                                      ),
                                      Spacer(),
                                      Text(
                                        widget.length,
                                        style: TextStyle(
                                          fontFamily: 'Gotham',
                                          fontWeight: FontWeight.w500,
                                          color: Colors.grey,
                                          fontSize: 14,
                                        ),
                                      ),
                                    ],
                                  )
                                ]
                            );
                          }),
                      SizedBox(height: 20,),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.center,
                        crossAxisAlignment: CrossAxisAlignment.center,
                        children: [
                          IconButton(
                            onPressed: () {},
                            padding: const EdgeInsets.only(top: 20.0),
                            color: Colors.white,
                            icon: const Icon(Icons.shuffle, size: 25.0),
                          ),
                          Spacer(),
                          IconButton(
                            onPressed: () {},
                            padding: const EdgeInsets.all(0.0),
                            color: Colors.white,
                            icon: const Icon(Icons.skip_previous_rounded, size: 60.0),
                          ),
                          Spacer(),
                          IconButton(
                            onPressed: () {
                              setState(() {
                                if(isPause){
                                  assetsAudioPlayer.play();
                                  controller.forward();
                                }else{
                                  assetsAudioPlayer.pause();
                                  controller.stop();
                                }
                                isPause = !isPause;
                              });
                            },
                            padding: const EdgeInsets.all(0.0),
                            color: Colors.white,
                            icon: isPause ? Icon(Icons.play_circle, size: 60.0) : Icon(Icons.pause_circle, size: 70.0),
                          ),
                          Spacer(),
                          IconButton(
                            onPressed: () {},
                            padding: const EdgeInsets.all(0.0),
                            color: Colors.white,
                            icon: const Icon(Icons.skip_next_rounded, size: 60.0),
                          ),
                          Spacer(),
                          IconButton(
                            onPressed: () {},
                            padding: const EdgeInsets.only(top: 20.0),
                            color: Colors.white,
                            icon: const Icon(Icons.loop_rounded, size: 25.0),
                          ),
                        ],
                      ),
                      SizedBox(height: 20,),
                      Row(
                        children: [
                          IconButton(
                            onPressed: () {},
                            padding: const EdgeInsets.only(top: 20.0),
                            color: Colors.white,
                            icon: const Icon(Icons.speaker, size: 25.0),
                          ),
                          Spacer(),
                          IconButton(
                            onPressed: () {},
                            padding: const EdgeInsets.only(top: 20.0),
                            color: Colors.white,
                            icon: const Icon(Icons.menu, size: 25.0),
                          ),
                        ],
                      ),
                      SizedBox(
                        height: mediaQuery.size.height * 0.05,
                      ),
                    ]
                )
            )
          )
        ],
      )
    );
  }
}

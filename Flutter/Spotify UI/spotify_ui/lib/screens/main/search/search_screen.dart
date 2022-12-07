import 'package:flutter/material.dart';
import 'package:spotify_ui/models/dataDummy.dart';
import 'package:spotify_ui/models/entity/GenreItem.dart';

class SearchScreen extends StatefulWidget {
  static const routeName = '/search_screen';

  const SearchScreen({Key? key}) : super(key: key);

  @override
  State<SearchScreen> createState() => _SearchScreenState();
}

class _SearchScreenState extends State<SearchScreen> {
  var top = 0.0;
  ScrollController scrollController = ScrollController();
  double _scrollPosition = 0.0;
  double _titleTextOpacity = 1.0;
  double _fontTitle = 60.0;

  @override
  void initState() {
    scrollController.addListener(() { //listener
      _scrollPosition = scrollController.offset;

      if(_scrollPosition >= 0.0 && _scrollPosition <60.0){
        _titleTextOpacity = (60 - (_scrollPosition / 60)) / 60;
      }else if(_scrollPosition >= 60.0){
        _titleTextOpacity = 0;
      }

      if(_scrollPosition >= 0.0 && _scrollPosition < 170.0){
        _fontTitle = 60 - (_scrollPosition - 60.0);
        if(_fontTitle <= 0 ) {
          _fontTitle = 0.0;
        }else if (_fontTitle >= 60){
          _fontTitle = 60.0;
        }
      }

      setState(() {});
    });
    super.initState();
  }

  Widget showGenreGrids(List<GenreItem> listGenre, double textWidth, double imgTop, double imgRight){
    return SizedBox(
      child: MediaQuery.removePadding(
        removeTop: true,
        context: context,
        child: GridView.count(
          shrinkWrap: true,
          physics: NeverScrollableScrollPhysics(),
          childAspectRatio: 3/2,
          crossAxisCount: 2,
          children:
          List.generate(listGenre.length, (index) {
            var data = listGenre[index];
            return Card(
              elevation: 4.0,
              shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(8.0)),
              clipBehavior: Clip.antiAlias,
              child: Container(
                padding: EdgeInsets.only(left: 20),
                color: data.bgColor,
                child: Stack(
                    children:[
                      Container(
                        margin: EdgeInsets.only(top: 20),
                        width: textWidth,
                        child: Text(
                          data.name,
                          style: TextStyle(
                              fontWeight: FontWeight.bold,
                              fontSize: 22,
                              color: Colors.white
                          ),
                        ),
                      ),
                      Positioned(
                        right: imgRight,
                        top: imgTop,
                        child: SizedBox(
                          width: 100,
                          height: 100,
                          child: RotationTransition(
                              turns: AlwaysStoppedAnimation(30 / 360),
                              child: Image.asset(
                                data.imagePath,
                                alignment: Alignment.centerRight,
                              )
                          ),
                        ),
                      ),
                    ]
                ),
              ),
            );
          }
          ),
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    final mediaQuery = MediaQuery.of(context);

    return Scaffold(
      body: Container(
        padding: EdgeInsets.only(top: 20, left: 20, right: 20),
        width: double.infinity,
        height: double.infinity,
        decoration: const BoxDecoration(
            gradient: LinearGradient(
              begin: Alignment.topCenter,
              end: Alignment.bottomCenter,
              colors: [
                Color.fromRGBO(64, 64, 64, 1),
                Colors.black87,
              ],
              stops: [
                0.1,
                0.4
              ],
            )
        ),
        child: Stack(
          children: [
            Container(
              child: AnimatedOpacity(
                duration: Duration(milliseconds: 250),
                opacity: _titleTextOpacity,
                child: Container(
                  padding: EdgeInsets.all(10),
                  alignment: Alignment.center,
                  height: 200,
                  child: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        Text(
                          "Search",
                          style: TextStyle(
                              color: Colors.white,
                              fontFamily: 'Gotham',
                              fontWeight: FontWeight.bold,
                              fontSize: _fontTitle
                          ),
                          textAlign: TextAlign.center,
                        ),
                      ]
                  ),
                ),
              ),
            ),
            SingleChildScrollView(
              controller: scrollController,
              child: Container(
                padding: EdgeInsets.only(top: 200),
                width: double.infinity,
                height: 3600,
                child: Column(
                  children: [
                    Row(
                      children: [
                        Expanded(
                          child: TextField(
                            obscureText: true,
                            decoration: InputDecoration(
                              border: OutlineInputBorder(),
                              labelText: 'Artist, song, or podcast',
                              prefixIcon: Icon(Icons.search),
                              fillColor: Colors.white,
                              filled: true,
                            ),
                            style: TextStyle(
                              color: Colors.grey,
                              fontWeight: FontWeight.bold,
                              fontSize: 22,
                            ),
                          ),
                        ),
                        SizedBox(width: 10),
                        TextButton(
                          child: Icon(Icons.mic_outlined),
                          onPressed: () {},
                          style: TextButton.styleFrom(
                            primary: Colors.white,
                          ),
                        ),
                      ],
                    ),
                    SizedBox(height: 10),
                    SizedBox(
                      width: double.infinity,
                      child: Text(
                        "Your top genre",
                        style: TextStyle(
                          color: Colors.white,
                          fontSize: 24,
                          fontWeight: FontWeight.bold
                        ),
                        textAlign: TextAlign.start,
                      ),
                    ),
                    SizedBox(height: 10),
                    showGenreGrids(
                      dataDummy().topGenres,
                      (mediaQuery.size.width - mediaQuery.padding.horizontal) * 0.35,
                      (mediaQuery.size.height - mediaQuery.padding.vertical) * 0.03,
                      -(mediaQuery.size.width - mediaQuery.padding.horizontal) * 0.055,
                    ),
                    SizedBox(height: 20),
                    SizedBox(
                      width: double.infinity,
                      child: Text(
                        "Browse all",
                        style: TextStyle(
                            color: Colors.white,
                            fontSize: 24,
                            fontWeight: FontWeight.bold
                        ),
                        textAlign: TextAlign.start,
                      ),
                    ),
                    SizedBox(height: 10),
                    showGenreGrids(
                      dataDummy().browseAll,
                      (mediaQuery.size.width - mediaQuery.padding.horizontal) * 0.35,
                      (mediaQuery.size.height - mediaQuery.padding.vertical) * 0.03,
                      -(mediaQuery.size.width - mediaQuery.padding.horizontal) * 0.055,
                    ),
                  ],
                ),
              ),
            )
          ],
        ),
      ),
    );
  }
}

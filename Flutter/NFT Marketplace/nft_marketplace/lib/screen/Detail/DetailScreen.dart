import 'package:flutter/material.dart';
import '../../model/DataSource.dart';
import '../../model/entities/NFT.dart';

class DetailScreen extends StatefulWidget {
  static const routeName = '/detail_screen';
  final NFT data;
  final bool isFavorite = false;

  const DetailScreen({
    super.key,
    required this.data
  });

  @override
  State<DetailScreen> createState() => _DetailScreenState();

}


class _DetailScreenState extends State<DetailScreen> with TickerProviderStateMixin {
  late bool _isFavorite;
  late AnimationController controller;

  var duration = 4329 ; //in second
  static const double ethToUsd = 1215.19;

  @override
  void initState() {
    _isFavorite = widget.isFavorite;

    controller = AnimationController(
      vsync: this,
      duration: Duration(seconds: duration),
    )..addListener(() {
      setState(() {});
    });
    controller.animateTo(duration.toDouble());
    super.initState();
  }

  void _updateFavorite() {
    setState(() {
      _isFavorite = !_isFavorite;
    });
  }

  @override
  void dispose() {
    controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final mediaQuery = MediaQuery.of(context);

    var creator = DataSource().creators.where(
            (c) => c.id == widget.data.creatorId
    ).toList();
    var collection = creator[0].collections.where(
            (col) => col.id == widget.data.collectionId
    ).toList()[0];

    Widget checkVerified(){
      if(creator[0].isVerified){
        return const Icon(
          Icons.verified,
          color: Colors.blue,
          size: 10,
        );
      }else{
        return const SizedBox();
      }
    }

    return Scaffold(
      appBar: AppBar(
        title: SizedBox(
          width: double.infinity,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              Text(
                collection.name,
                style: const TextStyle(
                    color: Colors.white,
                    fontFamily: "FredokaOne",
                    fontSize: 18,
                   fontWeight: FontWeight.bold
                ),
              ),
              const Text(
                  "1h 12m 09s",
                 style: TextStyle(
                   color: Colors.grey,
                   fontFamily: "FredokaOne",
                   fontSize: 14
                 ),
              ),
            ],
          ),
        ),
        actions: [
          IconButton(
              onPressed: (){},
              icon: const Icon(Icons.group_work_outlined)
          )
        ],
        leading: IconButton(
            onPressed: () {
              Navigator.pop(context);
            },
            icon: const Icon(Icons.arrow_back)
        ),
      ),
      body: Container(
        width: double.infinity,
        height: double.infinity,
        color: Colors.black,
        child: Column(
          children: [
            SizedBox(
              height: (mediaQuery.size.height - mediaQuery.padding.vertical) * 0.8,
              child: SingleChildScrollView(
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                  SizedBox(
                  width: double.infinity,
                  height: (mediaQuery.size.height - mediaQuery.padding.vertical) * 0.65,
                  child: Stack(
                      children: [
                        Container(
                            padding: const EdgeInsets.all(10),
                            width: double.infinity,
                            height: (mediaQuery.size.height - mediaQuery.padding.vertical) * 0.65,
                            decoration: BoxDecoration(
                              image: DecorationImage(
                                  image: AssetImage("assets/images/${widget.data.imagePath}"),
                                  fit: BoxFit.cover),
                            )
                        ),
                        Positioned.fill(
                          child: Align(
                            alignment: Alignment.bottomCenter,
                            child: Container(
                              height: 90,
                              padding: const EdgeInsets.all(10.0),
                              color: const Color.fromRGBO(40, 40, 40, .9),
                              child: Row(
                                mainAxisAlignment: MainAxisAlignment.start,
                                crossAxisAlignment: CrossAxisAlignment.center,
                                children: [
                                  CircleAvatar(
                                    backgroundColor: const Color.fromRGBO(30, 30, 30, .9),
                                    child: Container(
                                      padding: const EdgeInsets.all(10),
                                      child: Image.asset(
                                        "assets/images/ethereum-eth-logo.png"
                                      ),
                                    ),
                                  ),
                                  Expanded(
                                      child: Container(
                                        padding: const EdgeInsets.only(left: 10),
                                        child: Column(
                                          crossAxisAlignment: CrossAxisAlignment.start,
                                          mainAxisAlignment: MainAxisAlignment.center,
                                          children: [
                                            Row(
                                              children: [
                                                Text(
                                                  "${widget.data.price} ETH",
                                                  style: const TextStyle(
                                                      fontSize: 18,
                                                      color: Colors.white,
                                                      fontWeight: FontWeight.bold,
                                                      fontFamily: 'FredokaOne'
                                                  ),
                                                ),
                                                const SizedBox(width: 5),
                                                Text(
                                                  "\$${(widget.data.price * ethToUsd).toStringAsFixed(2)}",
                                                  style: const TextStyle(
                                                      fontSize: 16,
                                                      color: Color.fromRGBO(83, 242, 109, 1),
                                                      fontFamily: 'FredokaOne'
                                                  ),
                                                ),
                                              ],
                                            ),
                                            const SizedBox(height: 5),
                                            Row(
                                              children: [
                                                Text(
                                                  creator[0].name,
                                                  style: const TextStyle(
                                                      fontSize: 10,
                                                      color: Colors.grey,
                                                      fontWeight: FontWeight.bold,
                                                      fontFamily: 'FredokaOne'
                                                  ),
                                                ),
                                                const SizedBox(width: 5),
                                                checkVerified()
                                              ],
                                            ),
                                          ],
                                        ),
                                      )
                                  ),
                                  Container(
                                    margin: const EdgeInsets.symmetric(horizontal: 20),
                                    child: Column(
                                      mainAxisAlignment: MainAxisAlignment.center,
                                      crossAxisAlignment: CrossAxisAlignment.center,
                                      children: [
                                        IconButton(
                                            onPressed: () => _updateFavorite(),
                                            icon: Icon(
                                              _isFavorite ? Icons.favorite : Icons.favorite_border_outlined,
                                              color: _isFavorite ? Colors.red : Colors.white,
                                              size: 20,
                                            ),
                                        ),
                                        const SizedBox(height: 5),
                                        Text(
                                          _isFavorite ? (widget.data.totalFavorites + 1).toString() : widget.data.totalFavorites.toString(),
                                          style: const TextStyle(
                                              color: Colors.white,
                                              fontSize: 14,
                                              fontWeight: FontWeight.bold,
                                              fontFamily: "FredokaOne"
                                          ),
                                        )
                                      ],
                                    ),
                                  )
                                ],
                              ),
                            ),
                          ),
                        ),
                      ]
                    ),
                  ),
                    LinearProgressIndicator(
                      value: controller.value,
                      color: const Color.fromRGBO(83, 242, 109, 1),
                    ),
                    Container(
                      padding: const EdgeInsets.all(20),
                      color: Colors.black26,
                      child: Row(
                        children: [
                          Expanded(
                              child: Column(
                                mainAxisAlignment: MainAxisAlignment.center,
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: [
                                  Text(
                                    widget.data.name,
                                    style: const TextStyle(
                                        fontSize: 24,
                                        color: Colors.white,
                                        fontWeight: FontWeight.bold,
                                        fontFamily: 'FredokaOne'
                                    ),
                                  ),
                                  const Text(
                                    "by: Allen \t\t 12 in stock",
                                    style: TextStyle(
                                        fontSize: 14,
                                        color: Colors.grey,
                                        fontWeight: FontWeight.bold,
                                        fontFamily: 'FredokaOne'
                                    ),
                                  ),
                                ],
                              )
                          ),
                          Row(
                            crossAxisAlignment: CrossAxisAlignment.center,
                            mainAxisAlignment: MainAxisAlignment.spaceAround,
                            children: [
                              Column(
                                children: [
                                  IconButton(
                                      onPressed: () {},
                                      icon: const Icon(
                                        Icons.insert_chart_outlined,
                                        color: Colors.white,
                                        size: 30,
                                      )
                                  ),
                                  const Text(
                                    "History",
                                    style: TextStyle(
                                      fontSize: 12,
                                      color:Colors.grey
                                    ),
                                  )
                                ],
                              ),
                              const SizedBox(width: 10),
                              Column(
                                children: [
                                  IconButton(
                                      onPressed: () {},
                                      icon: const Icon(
                                        Icons.analytics_outlined,
                                        color: Colors.white,
                                        size: 30,
                                      )
                                  ),
                                  const Text(
                                    "Analysis",
                                    style: TextStyle(
                                        fontSize: 12,
                                        color:Colors.grey
                                    ),
                                  )
                                ],
                              )
                            ],
                          ),
                        ],
                      ),
                    ),
                    Container(
                      padding: const EdgeInsets.all(20),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          const Text(
                            "Description",
                            style: TextStyle(
                                fontSize: 24,
                                color: Colors.white,
                                fontWeight: FontWeight.bold,
                                fontFamily: 'FredokaOne'
                            ),
                          ),
                          const SizedBox(height: 10),
                          Text(
                            collection.description,
                            style: const TextStyle(
                                fontSize: 14,
                                color: Colors.white,
                                fontFamily: 'FredokaOne'
                            ),
                          ),
                        ],
                      )
                    )

                  ],
                ),
              ),
            ),
            SizedBox(
              height: (mediaQuery.size.height - mediaQuery.padding.vertical) * 0.1,
              child: Row(
                mainAxisAlignment: MainAxisAlignment.start,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  Container(
                    padding: const EdgeInsets.only(left: 10),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        Text(
                          "${widget.data.price} ETH",
                          style: const TextStyle(
                              fontSize: 18,
                              color: Color.fromRGBO(83, 242, 109, 1),
                              fontWeight: FontWeight.bold,
                              fontFamily: 'FredokaOne'
                          ),
                        ),
                        Row(
                          children: [
                            Text(
                              creator[0].name,
                              style: const TextStyle(
                                  fontSize: 10,
                                  color: Colors.grey,
                                  fontWeight: FontWeight.bold,
                                  fontFamily: 'FredokaOne'
                              ),
                            ),
                            const SizedBox(width: 5),
                            checkVerified()
                          ],
                        ),
                      ],
                    ),
                  ),
                  Expanded(
                      child: Container(
                        width: double.infinity,
                        margin: const EdgeInsets.symmetric(vertical: 10, horizontal: 15),
                        padding: const EdgeInsets.all(5),
                        decoration: const BoxDecoration(
                            color: Color.fromRGBO(83, 242, 109, 1)
                        ),
                        child: TextButton(
                          style: ButtonStyle(
                            foregroundColor: MaterialStateProperty.all<Color>(Colors.black),
                          ),
                          onPressed: () { },
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            crossAxisAlignment: CrossAxisAlignment.center,
                            children: const [
                              Icon(
                                  Icons.input
                              ),
                              SizedBox(width: 5),
                              Text(
                                'Bidding',
                                style: TextStyle(
                                  fontFamily: 'FredokaOne',
                                  color: Colors.black,
                                  fontSize: 14,
                                ),
                              ),
                            ],
                          ),
                        ),
                      )
                  )
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}

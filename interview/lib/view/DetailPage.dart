import 'package:flutter/material.dart';
import 'package:interview/model/CourseEntity.dart';

class DetailPage extends StatefulWidget {
  const DetailPage({Key? key}) : super(key: key);
  static const route_name = "/detail-page";

  @override
  State<DetailPage> createState() => _DetailPageState();
}

class _DetailPageState extends State<DetailPage> {

  var dummyData = [
    CourseEntity(
      name: 'Ilmu Pengetahuan Alam',
      lecturer: 'Mr. A',
      desc: 'Ini adalah mata pelajaran IPA',
      periode: '2021-2022'
    ),
    CourseEntity(
        name: 'Ilmu Pengetahuan Sosial',
        lecturer: 'Mr. B',
        desc: 'Ini adalah mata pelajaran IPS',
        periode: '2021-2022'
    ),
    CourseEntity(
        name: 'Sejarah',
        lecturer: 'Mr. C',
        desc: 'Ini adalah mata pelajaran yang mempelajari sejarah Indonesia',
        periode: '2021-2022'
    ),
    CourseEntity(
        name: 'Biologi',
        lecturer: 'Mr. D',
        desc: 'Ini adalah mata pelajaran yang khusus mempelajari mahluk hidup',
        periode: '2021-2022'
    ),
    CourseEntity(
        name: 'Geografi',
        lecturer: 'Mr. E',
        desc: 'Ini adalah mata pelajaran Geografi',
        periode: '2021-2022'
    ),
  ];

  int num = 0;

  void _btnCounter(){
    setState(() {
      num++;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Detail Page'),
      ),
      body: Column(
        children: [
          Text('This is second Page'),
        ],
      ),
    );
  }
}

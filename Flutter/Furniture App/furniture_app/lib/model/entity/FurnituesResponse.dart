import 'package:json_annotation/json_annotation.dart';

import 'FurniturItemResponse.dart';

@JsonSerializable()
class FurnituresResponse {
  int? status;
  String? source;
  List<FurniturItemResponse>? results;

  FurnituresResponse({this.status, this.source, this.results});

  FurnituresResponse.fromJson(Map<String, dynamic> json) {
    status = json['status'];
    source = json['source'];
    if (json['results'] != null) {
      results = <FurniturItemResponse>[];
      json['results'].forEach((v) {
        results!.add(FurniturItemResponse.fromJson(v));
      });
    }
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = Map<String, dynamic>();
    data['status'] = this.status;
    data['source'] = this.source;
    if (this.results != null) {
      data['results'] = this.results!.map((v) => v.toJson()).toList();
    }
    return data;
  }
}
import 'package:dio/dio.dart';
import 'package:furniture_app/model/entity/FurnituesResponse.dart';
import 'package:retrofit/retrofit.dart';

part 'api_helper.g.dart';

@RestApi(baseUrl: 'https://raw.githubusercontent.com/patriciafiona/patriciafiona.github.io/main/hosting/JSON')
abstract class RestClient {
  factory RestClient(Dio dio, {String baseUrl}) = _RestClient;

  @GET('/furnitures.json')
  Future<FurnituresResponse> getFurnitures();
}
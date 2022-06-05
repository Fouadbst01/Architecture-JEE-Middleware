import 'dart:convert';

import 'package:dio/dio.dart';

import '../models/part_model.dart';
import '../static/static_values.dart';

class ApiProvider {
  final Dio _dio = Dio();
  final String _url = '${StaticValue.apiUrl}/Part';

  Future<List<Part>> fetchPartsList() async {
    try {
      Response response = await _dio.get(_url);
      return (response.data as List).map((e) => Part.fromJson(e)).toList();
    } catch (error, stacktrace) {
      print("Exception occured: $error stackTrace: $stacktrace");
      return Future.error("Data not found / Connection issue"); //;
    }
  }
}
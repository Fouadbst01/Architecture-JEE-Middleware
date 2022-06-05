import 'dart:async';

import 'package:bloc/bloc.dart';
import 'package:e_afferai/repository/api_repository.dart';
import 'package:equatable/equatable.dart';
import 'package:meta/meta.dart';
import '../models/part_model.dart';

part 'parts_event.dart';
part 'parts_state.dart';

class PartsBloc extends Bloc<PartsEvent, PartsState> {
  PartsBloc() : super(PartsInitial()) {
    final ApiRepository _apiRepository = ApiRepository();
    on<GetPartsList>((event, emit) async{
      try {
        emit(PartsLoading());
        final mList = await _apiRepository.fetchPartsList();
        emit(PartsLoaded(mList));
        if (mList[0].error != null) {
          emit(PartsError(mList[0].error));
        }
      } on NetworkError {
        emit(PartsError("Failed to fetch data. is your device online?"));
      }
    });
  }
}

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:serves_app_lite_frontend/pages/home_page.dart';
import 'package:serves_app_lite_frontend/pages/login_page.dart';
import 'package:serves_app_lite_frontend/services/authentication_service.dart';

import 'blocs/authentication/authentication_bloc.dart';
import 'blocs/authentication/authentication_event.dart';
import 'blocs/authentication/authentication_state.dart';
import 'config/locator.dart';

void main() {
  //WidgetsFlutterBinding.ensureInitialized();
  //await SharedPreferences.getInstance();
  setupAsyncDependencies();
  configureDependencies();
  //await getIt.allReady();
  
    
    runApp(BlocProvider<AuthenticationBloc>(
        create: (context) {
          //GlobalContext.ctx = context;
          final authService = getIt<JwtAuthenticationService>();
          return AuthenticationBloc(authService)..add(AppLoaded());
        },
        child: MyApp(),
      ));

}

class GlobalContext {
  
  static late BuildContext ctx;

}


class MyApp extends StatelessWidget {

  //static late  AuthenticationBloc _authBloc;

  static late MyApp _instance;

  static Route route() {
    print("Enrutando al login");
    return MaterialPageRoute<void>(builder: (context) {
      var authBloc = BlocProvider.of<AuthenticationBloc>(context);
      authBloc..add(SessionExpiredEvent());
      return _instance;
    });
    /*return MaterialPageRoute<void>(builder: (context) {
      return BlocProvider<AuthenticationBloc>(create: (context) {
        final authService = getIt<JwtAuthenticationService>();
        return AuthenticationBloc(authService)..add(SessionExpiredEvent());
      }, 
      child: MyApp(),);
    });*/
  }

  MyApp() {
    _instance = this;
  }

  @override
  Widget build(BuildContext context) {
    //GlobalContext.ctx = context;
    return MaterialApp(
      title: 'Authentication Demo',
      theme: ThemeData(
        primarySwatch: Colors.teal,
      ),
      home: BlocBuilder<AuthenticationBloc, AuthenticationState>(
        builder: (context, state) {
          GlobalContext.ctx = context;
          if (state is AuthenticationAuthenticated) {
            // show home page
            return HomePage(
              user: state.user,
            );
          }
          // otherwise show login page
          return LoginPage();
        },
      ),
    );
  }
}
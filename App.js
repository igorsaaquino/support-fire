import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import { Provider as PaperProvider } from 'react-native-paper';
import { theme } from './src/styles/theme';

import HomeScreen from './src/screens/HomeScreen';
import RegisterScreen from './src/screens/RegisterScreen';
import SuccessScreen from './src/screens/SuccessScreen';
import AdminScreen from './src/screens/AdminScreen';

const Stack = createStackNavigator();

const App = () => {
  return (
    <PaperProvider theme={theme}>
      <NavigationContainer>
        <Stack.Navigator 
          initialRouteName="Home"
          screenOptions={{
            headerStyle: {
              backgroundColor: '#d32f2f',
            },
            headerTintColor: '#fff',
            headerTitleStyle: {
              fontWeight: 'bold',
            },
          }}
        >
          <Stack.Screen 
            name="Home" 
            component={HomeScreen} 
            options={{ title: 'Support Fire' }}
          />
          <Stack.Screen 
            name="Register" 
            component={RegisterScreen} 
            options={{ title: 'Pré-Inscrição' }}
          />
          <Stack.Screen 
            name="Success" 
            component={SuccessScreen} 
            options={{ title: 'Sucesso' }}
          />
          <Stack.Screen 
            name="Admin" 
            component={AdminScreen} 
            options={{ title: 'Administração' }}
          />
        </Stack.Navigator>
      </NavigationContainer>
    </PaperProvider>
  );
};

export default App;


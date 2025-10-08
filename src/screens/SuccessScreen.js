import React from 'react';
import { View, StyleSheet } from 'react-native';
import { Card, Title, Paragraph, Button, IconButton } from 'react-native-paper';
import { SafeAreaView } from 'react-native-safe-area-context';

const SuccessScreen = ({ navigation, route }) => {
  const { registrationData } = route.params || {};

  return (
    <SafeAreaView style={styles.container}>
      <View style={styles.content}>
        <Card style={styles.card}>
          <Card.Content style={styles.cardContent}>
            <IconButton
              icon="check-circle"
              size={80}
              iconColor="#4caf50"
              style={styles.icon}
            />
            <Title style={styles.title}>Pré-Inscrição Realizada!</Title>
            <Paragraph style={styles.paragraph}>
              Sua pré-inscrição no curso de Brigadista Mirim foi realizada com sucesso!
            </Paragraph>
            <Paragraph style={styles.paragraph}>
              Você receberá mais informações sobre o curso em breve através do e-mail cadastrado.
            </Paragraph>
            {registrationData && (
              <Card style={styles.infoCard}>
                <Card.Content>
                  <Title style={styles.infoTitle}>Dados da Inscrição</Title>
                  <Paragraph><strong>Nome:</strong> {registrationData.nome}</Paragraph>
                  <Paragraph><strong>E-mail:</strong> {registrationData.email}</Paragraph>
                  <Paragraph><strong>Telefone:</strong> {registrationData.telefone}</Paragraph>
                  <Paragraph><strong>Curso:</strong> {registrationData.cursoDesejado}</Paragraph>
                </Card.Content>
              </Card>
            )}
          </Card.Content>
        </Card>

        <View style={styles.buttonContainer}>
          <Button 
            mode="contained" 
            onPress={() => navigation.navigate('Home')}
            style={styles.button}
          >
            Voltar ao Início
          </Button>
          <Button 
            mode="outlined" 
            onPress={() => navigation.navigate('Register')}
            style={styles.button}
          >
            Nova Inscrição
          </Button>
        </View>
      </View>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#f5f5f5',
  },
  content: {
    flex: 1,
    padding: 16,
    justifyContent: 'center',
  },
  card: {
    elevation: 4,
  },
  cardContent: {
    alignItems: 'center',
    padding: 20,
  },
  icon: {
    marginBottom: 16,
  },
  title: {
    fontSize: 24,
    textAlign: 'center',
    marginBottom: 16,
    color: '#4caf50',
  },
  paragraph: {
    textAlign: 'center',
    marginBottom: 12,
    lineHeight: 22,
  },
  infoCard: {
    marginTop: 20,
    backgroundColor: '#f8f9fa',
  },
  infoTitle: {
    fontSize: 18,
    marginBottom: 12,
  },
  buttonContainer: {
    marginTop: 20,
  },
  button: {
    marginBottom: 12,
    paddingVertical: 8,
  },
});

export default SuccessScreen;


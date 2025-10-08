import React from 'react';
import { View, ScrollView, StyleSheet } from 'react-native';
import { Card, Title, Paragraph, Button } from 'react-native-paper';
import { SafeAreaView } from 'react-native-safe-area-context';

const HomeScreen = ({ navigation }) => {
  return (
    <SafeAreaView style={styles.container}>
      <ScrollView contentContainerStyle={styles.scrollContent}>
        <View style={styles.header}>
          <Title style={styles.title}>Support Fire</Title>
          <Paragraph style={styles.subtitle}>Curso de Brigadista Mirim</Paragraph>
        </View>

        <Card style={styles.card}>
          <Card.Content>
            <Title>Bem-vindo ao Support Fire</Title>
            <Paragraph style={styles.description}>
              O Support Fire propôs a criação do "Curso de Brigadista Mirim" como um instrumento, 
              atuando especialmente com foco na promoção da qualidade de vida, prevenção da 
              criminalidade e da violência, através de um conjunto estruturado de políticas 
              públicas voltadas para a inclusão social, integração e mobilização comunitária.
            </Paragraph>
          </Card.Content>
        </Card>

        <Card style={styles.card}>
          <Card.Content>
            <Title>Objetivos do Curso</Title>
            <Paragraph>
              • Defesa da vida{'\n'}
              • Respeito à cidadania{'\n'}
              • Garantia dos direitos fundamentais da criança e do adolescente{'\n'}
              • Prevenção e combate a incêndios{'\n'}
              • Primeiros socorros{'\n'}
              • Preservação do meio ambiente
            </Paragraph>
          </Card.Content>
        </Card>

        <Card style={styles.card}>
          <Card.Content>
            <Title>Conteúdo Programático</Title>
            <Paragraph>
              • Defesa Civil{'\n'}
              • Primeiros Socorros{'\n'}
              • Combate a Incêndio{'\n'}
              • Oceanografia{'\n'}
              • Preservação do Meio Ambiente{'\n'}
              • Doenças Sexualmente Transmissíveis{'\n'}
              • Drogas e seus Malefícios{'\n'}
              • Profissões{'\n'}
              • Acidentes Domésticos{'\n'}
              • Acidentes Automobilísticos{'\n'}
              • Animais Peçonhentos{'\n'}
              • Acionamento dos Órgãos Públicos nos Eventos Adversos
            </Paragraph>
          </Card.Content>
        </Card>

        <Card style={styles.card}>
          <Card.Content>
            <Title>Autorização</Title>
            <Paragraph>
              Este curso é autorizado pelo Corpo de Bombeiros do Estado do Rio de Janeiro, 
              garantindo qualidade e credibilidade na formação dos nossos brigadistas mirins.
            </Paragraph>
          </Card.Content>
        </Card>

        <View style={styles.buttonContainer}>
          <Button 
            mode="contained" 
            onPress={() => navigation.navigate('Register')}
            style={styles.button}
            contentStyle={styles.buttonContent}
          >
            Fazer Pré-Inscrição
          </Button>
          
          <Button 
            mode="outlined" 
            onPress={() => navigation.navigate('Admin')}
            style={styles.adminButton}
            contentStyle={styles.buttonContent}
          >
            Área Administrativa
          </Button>
        </View>
      </ScrollView>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#f5f5f5',
  },
  scrollContent: {
    padding: 16,
  },
  header: {
    alignItems: 'center',
    marginBottom: 20,
    paddingVertical: 20,
  },
  title: {
    fontSize: 28,
    fontWeight: 'bold',
    color: '#d32f2f',
    textAlign: 'center',
  },
  subtitle: {
    fontSize: 16,
    color: '#666',
    textAlign: 'center',
    marginTop: 5,
  },
  card: {
    marginBottom: 16,
    elevation: 4,
  },
  description: {
    textAlign: 'justify',
    lineHeight: 22,
  },
  buttonContainer: {
    marginTop: 20,
    marginBottom: 20,
  },
  button: {
    marginBottom: 12,
    paddingVertical: 8,
  },
  adminButton: {
    marginBottom: 12,
    paddingVertical: 8,
  },
  buttonContent: {
    paddingVertical: 8,
  },
});

export default HomeScreen;


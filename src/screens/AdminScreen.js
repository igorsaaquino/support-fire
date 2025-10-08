import React, { useState, useEffect } from 'react';
import { View, ScrollView, StyleSheet, Alert } from 'react-native';
import { 
  Card, 
  Title, 
  Paragraph, 
  Button, 
  DataTable, 
  Searchbar,
  FAB,
  Portal,
  Modal,
  List,
  Divider
} from 'react-native-paper';
import { SafeAreaView } from 'react-native-safe-area-context';
import AsyncStorage from '@react-native-async-storage/async-storage';
import RNFS from 'react-native-fs';
import Share from 'react-native-share';

const AdminScreen = ({ navigation }) => {
  const [registrations, setRegistrations] = useState([]);
  const [filteredRegistrations, setFilteredRegistrations] = useState([]);
  const [searchQuery, setSearchQuery] = useState('');
  const [loading, setLoading] = useState(false);
  const [showDetails, setShowDetails] = useState(false);
  const [selectedRegistration, setSelectedRegistration] = useState(null);

  useEffect(() => {
    loadRegistrations();
  }, []);

  useEffect(() => {
    filterRegistrations();
  }, [searchQuery, registrations]);

  const loadRegistrations = async () => {
    try {
      const data = await AsyncStorage.getItem('registrations');
      if (data) {
        const parsedData = JSON.parse(data);
        setRegistrations(parsedData);
      }
    } catch (error) {
      Alert.alert('Erro', 'Erro ao carregar os dados');
    }
  };

  const filterRegistrations = () => {
    if (!searchQuery.trim()) {
      setFilteredRegistrations(registrations);
      return;
    }

    const filtered = registrations.filter(reg => 
      reg.nome.toLowerCase().includes(searchQuery.toLowerCase()) ||
      reg.email.toLowerCase().includes(searchQuery.toLowerCase()) ||
      reg.cidade.toLowerCase().includes(searchQuery.toLowerCase())
    );
    setFilteredRegistrations(filtered);
  };

  const exportToCSV = async () => {
    try {
      setLoading(true);
      
      if (registrations.length === 0) {
        Alert.alert('Aviso', 'Não há dados para exportar');
        return;
      }

      // Cabeçalho do CSV
      const headers = [
        'ID',
        'Nome',
        'E-mail',
        'Telefone',
        'Sexo',
        'Tipo Sanguíneo',
        'Endereço',
        'Bairro',
        'Cidade',
        'Estado',
        'Data de Nascimento',
        'Pai',
        'Mãe',
        'Curso Desejado',
        'Como Soube',
        'Data do Cadastro'
      ];

      // Dados do CSV
      const csvData = registrations.map(reg => [
        reg.id,
        reg.nome,
        reg.email,
        reg.telefone,
        reg.sexo,
        reg.tipoSanguineo,
        reg.endereco,
        reg.bairro,
        reg.cidade,
        reg.estado,
        reg.dataNascimento,
        reg.pai,
        reg.mae,
        reg.cursoDesejado,
        reg.comoSoube,
        new Date(reg.dataCadastro).toLocaleDateString('pt-BR')
      ]);

      // Converter para CSV
      const csvContent = [headers, ...csvData]
        .map(row => row.map(field => `"${field}"`).join(','))
        .join('\n');

      // Salvar arquivo
      const fileName = `support_fire_cadastros_${new Date().toISOString().split('T')[0]}.csv`;
      const filePath = `${RNFS.DownloadDirectoryPath}/${fileName}`;
      
      await RNFS.writeFile(filePath, csvContent, 'utf8');

      // Compartilhar arquivo
      const shareOptions = {
        title: 'Cadastros Support Fire',
        message: 'Arquivo CSV com os cadastros do Support Fire',
        url: `file://${filePath}`,
        type: 'text/csv',
      };

      await Share.open(shareOptions);
      Alert.alert('Sucesso', 'Arquivo CSV exportado com sucesso!');
    } catch (error) {
      Alert.alert('Erro', 'Erro ao exportar dados: ' + error.message);
    } finally {
      setLoading(false);
    }
  };

  const showRegistrationDetails = (registration) => {
    setSelectedRegistration(registration);
    setShowDetails(true);
  };

  const formatDate = (dateString) => {
    return new Date(dateString).toLocaleDateString('pt-BR');
  };

  return (
    <SafeAreaView style={styles.container}>
      <ScrollView contentContainerStyle={styles.scrollContent}>
        <Card style={styles.card}>
          <Card.Content>
            <Title>Área Administrativa</Title>
            <Paragraph>Total de cadastros: {registrations.length}</Paragraph>
          </Card.Content>
        </Card>

        <Searchbar
          placeholder="Buscar por nome, e-mail ou cidade..."
          onChangeText={setSearchQuery}
          value={searchQuery}
          style={styles.searchbar}
        />

        <Card style={styles.tableCard}>
          <Card.Content>
            <DataTable>
              <DataTable.Header>
                <DataTable.Title>Nome</DataTable.Title>
                <DataTable.Title>E-mail</DataTable.Title>
                <DataTable.Title>Data</DataTable.Title>
                <DataTable.Title>Ações</DataTable.Title>
              </DataTable.Header>

              {filteredRegistrations.map((registration) => (
                <DataTable.Row key={registration.id}>
                  <DataTable.Cell>{registration.nome}</DataTable.Cell>
                  <DataTable.Cell>{registration.email}</DataTable.Cell>
                  <DataTable.Cell>{formatDate(registration.dataCadastro)}</DataTable.Cell>
                  <DataTable.Cell>
                    <Button 
                      mode="text" 
                      onPress={() => showRegistrationDetails(registration)}
                      compact
                    >
                      Ver
                    </Button>
                  </DataTable.Cell>
                </DataTable.Row>
              ))}
            </DataTable>
          </Card.Content>
        </Card>

        <Button 
          mode="contained" 
          onPress={exportToCSV}
          loading={loading}
          style={styles.exportButton}
        >
          Exportar CSV
        </Button>
      </ScrollView>

      <FAB
        icon="plus"
        style={styles.fab}
        onPress={() => navigation.navigate('Register')}
      />

      <Portal>
        <Modal
          visible={showDetails}
          onDismiss={() => setShowDetails(false)}
          contentContainerStyle={styles.modalContent}
        >
          {selectedRegistration && (
            <ScrollView>
              <Title>Detalhes do Cadastro</Title>
              <Divider style={styles.divider} />
              
              <List.Item
                title="Nome"
                description={selectedRegistration.nome}
              />
              <List.Item
                title="E-mail"
                description={selectedRegistration.email}
              />
              <List.Item
                title="Telefone"
                description={selectedRegistration.telefone}
              />
              <List.Item
                title="Sexo"
                description={selectedRegistration.sexo}
              />
              <List.Item
                title="Tipo Sanguíneo"
                description={selectedRegistration.tipoSanguineo}
              />
              <List.Item
                title="Endereço"
                description={selectedRegistration.endereco}
              />
              <List.Item
                title="Bairro"
                description={selectedRegistration.bairro}
              />
              <List.Item
                title="Cidade"
                description={selectedRegistration.cidade}
              />
              <List.Item
                title="Estado"
                description={selectedRegistration.estado}
              />
              <List.Item
                title="Data de Nascimento"
                description={formatDate(selectedRegistration.dataNascimento)}
              />
              <List.Item
                title="Pai"
                description={selectedRegistration.pai}
              />
              <List.Item
                title="Mãe"
                description={selectedRegistration.mae}
              />
              <List.Item
                title="Curso Desejado"
                description={selectedRegistration.cursoDesejado}
              />
              <List.Item
                title="Como soube do curso"
                description={selectedRegistration.comoSoube}
              />
              <List.Item
                title="Data do Cadastro"
                description={formatDate(selectedRegistration.dataCadastro)}
              />

              <Button 
                mode="contained" 
                onPress={() => setShowDetails(false)}
                style={styles.closeButton}
              >
                Fechar
              </Button>
            </ScrollView>
          )}
        </Modal>
      </Portal>
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
    paddingBottom: 80,
  },
  card: {
    marginBottom: 16,
    elevation: 4,
  },
  searchbar: {
    marginBottom: 16,
  },
  tableCard: {
    marginBottom: 16,
    elevation: 4,
  },
  exportButton: {
    marginTop: 16,
    paddingVertical: 8,
  },
  fab: {
    position: 'absolute',
    margin: 16,
    right: 0,
    bottom: 0,
    backgroundColor: '#d32f2f',
  },
  modalContent: {
    backgroundColor: 'white',
    padding: 20,
    margin: 20,
    borderRadius: 8,
    maxHeight: '80%',
  },
  divider: {
    marginVertical: 16,
  },
  closeButton: {
    marginTop: 20,
  },
});

export default AdminScreen;


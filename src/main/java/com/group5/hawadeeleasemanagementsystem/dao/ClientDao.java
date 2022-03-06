package com.group5.hawadeeleasemanagementsystem.dao;
import com.group5.hawadeeleasemanagementsystem.domain.Client;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ClientDao {
    void addClient(Client client);
    Client selectClient(Client client);
    List<Client> getClients();
    List<String> getAllEmail();
}

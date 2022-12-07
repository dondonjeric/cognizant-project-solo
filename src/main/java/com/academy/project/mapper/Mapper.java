package com.academy.project.mapper;

import com.academy.project.dto.CreateCommunityAdminAndManagerRequest;
import com.academy.project.model.CommunityAdminAndManager;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.stereotype.Component;

@Component
public class Mapper extends ModelMapper {

    public Mapper() {
        this.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.addMappings(createMapper());
    }

    private PropertyMap<CreateCommunityAdminAndManagerRequest, CommunityAdminAndManager> createMapper(){
        return new PropertyMap<CreateCommunityAdminAndManagerRequest, CommunityAdminAndManager>() {
            @Override
            protected void configure() {
                map().setName(source.getName());
                map().setCognizantId(source.getCognizantId());
                map().setCsvEmail(source.getCsvEmail());
                map().setRoleType(source.getRoleType());
                map().setPassword(source.getPassword());
            }
        };
    }
}

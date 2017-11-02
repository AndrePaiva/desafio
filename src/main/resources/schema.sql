create table meta (id bigint generated by default as identity, descricao varchar(255), meta_id bigint, orgao_id bigint not null, primary key (id));
create table orgao (id bigint generated by default as identity, descricao varchar(255), primary key (id));
create table orgao_setores (orgao_id bigint not null, setores_id bigint not null);
create table setor (id bigint generated by default as identity, descricao varchar(255), primary key (id));
alter table setor add constraint UK_tf5wbf7hmuyrhd2m2y6tmwrl5 unique (descricao);
alter table meta add constraint FKsx0qdf35eo1rm81ahv3b6fo3n foreign key (meta_id) references meta;
alter table meta add constraint FKeww89vfy2sfowpwnibv8ehgpn foreign key (orgao_id) references orgao;
alter table orgao_setores add constraint FKdbk6767l4cm8ijwlowou1vctk foreign key (setores_id) references setor;
alter table orgao_setores add constraint FK8d94cf3hxirlewaaq5wvdrn04 foreign key (orgao_id) references orgao;